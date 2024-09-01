package com.github.winkathon.lingo.domain.post.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.github.winkathon.lingo.domain.post.dto.request.BuyPostRequest;
import com.github.winkathon.lingo.domain.post.dto.request.CreatePostRequest;
import com.github.winkathon.lingo.domain.post.dto.response.GetPostResponse;
import com.github.winkathon.lingo.domain.post.dto.response.GetPostsResponse;
import com.github.winkathon.lingo.domain.post.exception.AlreadyPaidPostException;
import com.github.winkathon.lingo.domain.post.exception.ExistsPostTitleException;
import com.github.winkathon.lingo.domain.post.exception.ImageNotFoundException;
import com.github.winkathon.lingo.domain.post.exception.NotOwnPostException;
import com.github.winkathon.lingo.domain.post.exception.PostNotFoundException;
import com.github.winkathon.lingo.domain.post.repository.PostRepository;
import com.github.winkathon.lingo.domain.post.schema.Post;
import com.github.winkathon.lingo.domain.post.util.TossApi;
import com.github.winkathon.lingo.domain.upload.repository.ImageRepository;
import com.github.winkathon.lingo.domain.upload.schema.Image;
import com.github.winkathon.lingo.domain.user.repository.UserRepository;
import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TossApi tossApi;
    private final ImageRepository imageRepository;

    public GetPostsResponse getPosts() {

        List<Post> posts = postRepository.findAll().stream()
                .peek(post -> post.setContent(null))
                .toList();

        return GetPostsResponse.builder()
                .posts(posts)
                .build();
    }

    public GetPostsResponse search(String keyword) {

        Set<Post> posts = new HashSet<>();

        postRepository.findAll().forEach(post -> {

            if (post.getIntroduce().contains(keyword) || post.getTitle().contains(keyword)) {

                posts.add(post);
            }
        });

        posts.forEach(post -> post.setContent(null));

        return GetPostsResponse.builder()
                .posts(posts.stream().toList())
                .build();
    }

    public GetPostsResponse getPaidPosts(User user) {

        List<Post> posts = user.getPaidPosts();

        posts.forEach(post -> post.setContent(null));

        return GetPostsResponse.builder()
                .posts(posts)
                .build();
    }

    public GetPostsResponse getSavedPosts(User user) {

        List<Post> posts = user.getSavedPosts();

        posts.forEach(post -> post.setContent(null));

        return GetPostsResponse.builder()
                .posts(posts)
                .build();
    }

    public GetPostsResponse getOwnedPosts(User user) {

        List<Post> posts = postRepository.findAll();

        posts.removeIf(post -> !post.getOwner().equals(user));

        posts.forEach(post -> post.setContent(null));

        return GetPostsResponse.builder()
                .posts(posts)
                .build();
    }

    public GetPostResponse getPost(User user, String postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if (user == null) {
            if (post.isPaid()) {

                post.setContent(null);
            }
        } else {
            if (!post.getOwner().equals(user) && post.isPaid() && !user.getPaidPosts().contains(post)) {

                post.setContent(null);
            }
        }

        return GetPostResponse.builder()
                .post(post)
                .build();
    }

    public void createPost(User user, CreatePostRequest dto) {

        String title = dto.title();
        String content = dto.content();
        String introduce = dto.introduce();
        boolean paid = dto.isPaid();
        int price = dto.price();
        String imageId = dto.imageId();
        Image image = imageRepository.findById(imageId)
                .orElseThrow(ImageNotFoundException::new);

        if (postRepository.existsByTitle(title)) {

            throw new ExistsPostTitleException();
        }

        Post post = Post.builder()
                .owner(user)
                .title(title)
                .content(content)
                .introduce(introduce)
                .paid(paid)
                .price(price)
                .backgroundImage(image)
                .build();

        postRepository.save(post);
    }

    public void updatePost(User user, String postId, CreatePostRequest dto) {

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if (!post.getOwner().equals(user)) {

            throw new NotOwnPostException();
        }

        String title = dto.title();
        String content = dto.content();
        String introduce = dto.introduce();
        boolean paid = dto.isPaid();
        int price = dto.price();
        String imageId = dto.imageId();

        Image image = imageRepository.findById(imageId)
                .orElseThrow(ImageNotFoundException::new);

        post.setTitle(title);
        post.setContent(content);
        post.setIntroduce(introduce);
        post.setPaid(paid);
        post.setPrice(price);
        post.setBackgroundImage(image);

        postRepository.save(post);
    }

    public void deletePost(User user, String postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if (!post.getOwner().equals(user)) {

            throw new NotOwnPostException();
        }

        postRepository.delete(post);
    }

    public void buyPost(User user, String postId, BuyPostRequest dto) {

        String orderId = dto.orderId();
        String paymentKey = dto.paymentKey();
        int amount = dto.amount();

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if (user.getPaidPosts().contains(post) || post.getOwner().equals(user)) {

            throw new AlreadyPaidPostException();
        }

        tossApi.pay(orderId, paymentKey, amount);

        user.getPaidPosts().add(post);
        userRepository.save(user);
    }

    public void savePost(User user, String postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if (post.getOwner().equals(user)) {

            throw new NotOwnPostException();
        }

        user.getSavedPosts().add(post);

        userRepository.save(user);
    }

    public void unsavePost(User user, String postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        user.getSavedPosts().remove(post);
        userRepository.save(user);
    }

}
