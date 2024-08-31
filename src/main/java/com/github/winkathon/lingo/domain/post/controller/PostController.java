package com.github.winkathon.lingo.domain.post.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.winkathon.lingo.common.api.dto.response.ApiResponse;
import com.github.winkathon.lingo.common.security.util.UserContext;
import com.github.winkathon.lingo.domain.post.dto.request.BuyPostRequest;
import com.github.winkathon.lingo.domain.post.dto.request.CreatePostRequest;
import com.github.winkathon.lingo.domain.post.dto.response.GetPostResponse;
import com.github.winkathon.lingo.domain.post.dto.response.GetPostsResponse;
import com.github.winkathon.lingo.domain.post.dto.response.UploadResponse;
import com.github.winkathon.lingo.domain.post.service.PostService;
import com.github.winkathon.lingo.domain.post.service.UploadService;
import com.github.winkathon.lingo.domain.user.schema.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UploadService uploadService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ApiResponse<GetPostsResponse> getPosts() {

        return ApiResponse.ok(postService.getPosts());
    }

    @GetMapping("/search")
    @PreAuthorize("permitAll()")
    public ApiResponse<GetPostsResponse> search(@RequestParam String keyword) {

        return ApiResponse.ok(postService.search(keyword));
    }

    @GetMapping("/paid")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<GetPostsResponse> getPaidPosts() {

        User user = UserContext.getUser();

        return ApiResponse.ok(postService.getPaidPosts(user));
    }

    @GetMapping("/saved")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<GetPostsResponse> getSavedPosts() {

        User user = UserContext.getUser();

        return ApiResponse.ok(postService.getSavedPosts(user));
    }

    @GetMapping("/owned")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<GetPostsResponse> getOwnedPosts() {

        User user = UserContext.getUser();

        return ApiResponse.ok(postService.getOwnedPosts(user));
    }

    @GetMapping("/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<GetPostResponse> getPost(@PathVariable String postId) {

        User user = UserContext.getUser();

        return ApiResponse.ok(postService.getPost(user, postId));
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> createPost(@RequestBody @Valid CreatePostRequest request) {

        User user = UserContext.getUser();

        postService.createPost(user, request);

        return ApiResponse.ok();
    }

    @PutMapping("/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> updatePost(@PathVariable String postId, @RequestBody @Valid CreatePostRequest request) {

        User user = UserContext.getUser();

        postService.updatePost(user, postId, request);

        return ApiResponse.ok();
    }

    @DeleteMapping("/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> deletePost(@PathVariable String postId) {

        User user = UserContext.getUser();

        postService.deletePost(user, postId);

        return ApiResponse.ok();
    }

    @PostMapping("/{postId}/buy")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> buyPost(@PathVariable String postId, @RequestBody @Valid BuyPostRequest request) {

        User user = UserContext.getUser();

        postService.buyPost(user, postId, request);

        return ApiResponse.ok();
    }

    @PutMapping("/{postId}/save")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> savePost(@PathVariable String postId) {

        User user = UserContext.getUser();

        postService.savePost(user, postId);

        return ApiResponse.ok();
    }

    @DeleteMapping("/{postId}/save")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> unsavePost(@PathVariable String postId) {

        User user = UserContext.getUser();

        postService.unsavePost(user, postId);

        return ApiResponse.ok();
    }

    @PostMapping("/upload")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<UploadResponse> upload(@RequestParam("file") MultipartFile file) {

        User user = UserContext.getUser();
        
        return ApiResponse.ok(uploadService.upload(user, file));
    }
}
