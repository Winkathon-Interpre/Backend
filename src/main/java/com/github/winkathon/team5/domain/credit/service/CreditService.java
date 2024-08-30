package com.github.winkathon.team5.domain.credit.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.winkathon.team5.domain.credit.dto.request.BuyCreditRequest;
import com.github.winkathon.team5.domain.credit.dto.response.BuyCreditResponse;
import com.github.winkathon.team5.domain.credit.exception.BuyFailException;
import com.github.winkathon.team5.domain.credit.util.TossApi;
import com.github.winkathon.team5.domain.user.repository.UserRepository;
import com.github.winkathon.team5.domain.user.schema.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditService {

    private final UserRepository userRepository;
    private final TossApi tossApi;

    public BuyCreditResponse buyCredit(User user, BuyCreditRequest dto) {

        String orderId = dto.orderId();
        String paymentKey = dto.paymentKey();
        int amount = dto.amount();

        Map<String, Object> tossResponse = tossApi.pay(orderId, paymentKey, amount);

        if (!tossResponse.get("status").equals("DONE")) {

            throw new BuyFailException();
        }

        user.setCredit(user.getCredit() + amount);

        userRepository.save(user);

        return BuyCreditResponse.builder()
                .addedCredit(amount)
                .totalCredit(user.getCredit())
                .build();
    }
}
