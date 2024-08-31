package com.github.winkathon.lingo.domain.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.winkathon.lingo.domain.comment.dto.request.CreateCommentRequest;
import com.github.winkathon.lingo.domain.comment.dto.response.GetCommentsResponse;
import com.github.winkathon.lingo.domain.comment.exception.CommentNotFoundException;
import com.github.winkathon.lingo.domain.comment.repository.CommentRepository;
import com.github.winkathon.lingo.domain.comment.schema.Comment;
import com.github.winkathon.lingo.domain.notification.repository.NotificationRepository;
import com.github.winkathon.lingo.domain.notification.schema.Notification;
import com.github.winkathon.lingo.domain.post.exception.PostNotFoundException;
import com.github.winkathon.lingo.domain.post.repository.PostRepository;
import com.github.winkathon.lingo.domain.post.schema.Post;
import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final NotificationRepository notificationRepository;

    public GetCommentsResponse getCommentsByPostId(User user, String postId) {

        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        List<Comment> comments = commentRepository.findAllByPost(post);

        if (!post.getOwner().equals(user)) {

            comments.forEach(comment -> {

                if (!comment.getOwner().equals(user) && comment.isAnonymous()) {

                    comment.setContent(null);
                }
            });
        }

        return GetCommentsResponse.builder()
                .comments(comments)
                .build();
    }

    public void createContent(User user, String postId, CreateCommentRequest dto) {

        String content = dto.content();
        boolean anonymous = dto.anonymous();

        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        Comment comment = Comment.builder()
                .post(post)
                .owner(user)
                .content(content)
                .anonymous(anonymous)
                .build();

        Notification notification = Notification.builder()
                .owner(post.getOwner())
                .title(post.getTitle())
                .content(comment.getContent())
                .read(false)
                .redirectUrl("")
                .build();

        notificationRepository.save(notification);

        commentRepository.save(comment);
    }

    public void replyContent(User user, String commentId, CreateCommentRequest dto) {

        String content = dto.content();
        boolean anonymous = dto.anonymous();

        Comment orifinalComment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);

        Comment comment = Comment.builder()
                .post(orifinalComment.getPost())
                .comment(orifinalComment)
                .owner(user)
                .content(content)
                .anonymous(anonymous)
                .build();

        Notification notification = Notification.builder()
                .owner(orifinalComment.getOwner())
                .title("댓글이 달렸습니다.")
                .content(comment.getContent())
                .read(false)
                .redirectUrl("")
                .build();

        notificationRepository.save(notification);

        commentRepository.save(comment);
    }

    public void updateComment(User user, String commentId, CreateCommentRequest dto) {

        String content = dto.content();

        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);

        if (!comment.getOwner().equals(user)) {

            throw new CommentNotFoundException();
        }

        comment.setContent(content);

        commentRepository.save(comment);
    }

    public void deleteComment(User user, String commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);

        if (!comment.getOwner().equals(user)) {

            throw new CommentNotFoundException();
        }

        commentRepository.deleteAll(commentRepository.findAllByComment(comment));

        commentRepository.delete(comment);
    }
}
