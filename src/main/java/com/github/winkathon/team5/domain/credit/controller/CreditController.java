package com.github.winkathon.team5.domain.credit.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.winkathon.team5.common.api.dto.response.ApiResponse;
import com.github.winkathon.team5.common.security.util.UserContext;
import com.github.winkathon.team5.domain.credit.dto.request.BuyCreditRequest;
import com.github.winkathon.team5.domain.credit.dto.response.BuyCreditResponse;
import com.github.winkathon.team5.domain.credit.service.CreditService;
import com.github.winkathon.team5.domain.user.schema.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/credit")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @PostMapping("/buy")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<BuyCreditResponse> buyCredit(@RequestBody @Valid BuyCreditRequest request) {

        User user = UserContext.getUser();

        return ApiResponse.ok(creditService.buyCredit(user, request));
    }
}
