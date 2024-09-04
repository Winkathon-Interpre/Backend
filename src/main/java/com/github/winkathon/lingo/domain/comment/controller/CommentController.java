package com.github.winkathon.lingo.domain.comment.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.winkathon.lingo.common.api.dto.response.ApiResponse;
import com.github.winkathon.lingo.common.security.util.UserContext;
import com.github.winkathon.lingo.domain.comment.dto.request.CreateCommentRequest;
import com.github.winkathon.lingo.domain.comment.dto.response.GetCommentsResponse;
import com.github.winkathon.lingo.domain.comment.service.CommentService;
import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<GetCommentsResponse> getCommentsByPostId(@PathVariable String postId) {

        User user = UserContext.getUser();

        return ApiResponse.ok(commentService.getCommentsByPostId(user, postId));
    }

    @PostMapping("/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> createCommentFromPost(@PathVariable String postId,
            @RequestBody CreateCommentRequest request) {

        User user = UserContext.getUser();

        commentService.createContent(user, postId, request);

        return ApiResponse.ok();
    }

    @PostMapping("/reply/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> createCommentFromComment(@PathVariable String commentId,
            @RequestBody CreateCommentRequest request) {

        User user = UserContext.getUser();

        commentService.replyContent(user, commentId, request);

        return ApiResponse.ok();
    }

    @PutMapping("/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> updateComment(@PathVariable String commentId,
            @RequestBody CreateCommentRequest request) {

        User user = UserContext.getUser();

        commentService.updateComment(user, commentId, request);

        return ApiResponse.ok();
    }

    @DeleteMapping("/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Void> deleteComment(@PathVariable String commentId) {

        User user = UserContext.getUser();

        commentService.deleteComment(user, commentId);

        return ApiResponse.ok();
    }
}
