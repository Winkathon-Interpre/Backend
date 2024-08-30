package com.github.winkathon.team5.domain.subscription.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.winkathon.team5.common.api.dto.response.ApiResponse;
import com.github.winkathon.team5.common.security.util.UserContext;
import com.github.winkathon.team5.domain.subscription.dto.SubscribeRequest;
import com.github.winkathon.team5.domain.subscription.service.SubscriptionService;
import com.github.winkathon.team5.domain.user.schema.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public ApiResponse<Void> subscribe(@RequestBody @Valid SubscribeRequest request) {

        User user = UserContext.getUser();

        subscriptionService.subscribe(user, request);

        return ApiResponse.ok();
    }

    @PostMapping("/unsubscribe")
    public ApiResponse<Void> unsubscribe() {

        User user = UserContext.getUser();

        subscriptionService.unsubscribe(user);

        return ApiResponse.ok();
    }
}
