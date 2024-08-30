package com.github.winkathon.team5.domain.subscription.service;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.github.winkathon.team5.domain.subscription.dto.SubscribeRequest;
import com.github.winkathon.team5.domain.subscription.exception.AlreadySubscribedException;
import com.github.winkathon.team5.domain.subscription.exception.NoSubscriptionException;
import com.github.winkathon.team5.domain.subscription.exception.SubscribeFailException;
import com.github.winkathon.team5.domain.subscription.util.TossApi;
import com.github.winkathon.team5.domain.user.repository.UserRepository;
import com.github.winkathon.team5.domain.user.schema.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final UserRepository userRepository;
    private final TossApi tossApi;

    public void subscribe(User user, SubscribeRequest dto) {

        String orderId = dto.orderId();
        String paymentKey = dto.paymentKey();
        int amount = dto.amount();

        if (isSubscribed(user)) {

            throw new AlreadySubscribedException();
        }

        Map<String, Object> tossResponse = tossApi.pay(orderId, paymentKey, amount);

        if (!tossResponse.get("status").equals("DONE")) {

            throw new SubscribeFailException();
        }

        user.setSubscriptionStartDate(LocalDate.now());

        userRepository.save(user);
    }

    public void unsubscribe(User user) {

        if (!isSubscribed(user)) {

            throw new NoSubscriptionException();
        }

        user.setSubscriptionStartDate(null);

        userRepository.save(user);
    }

    public boolean isSubscribed(User user) {

        return user.getSubscriptionStartDate() != null;
    }

    public boolean isExpired(User user) {

        return user.getSubscriptionStartDate()
                .plusMonths(1)
                .isBefore(LocalDate.now());
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void checkSubscription() {

        userRepository.findAll().stream()
                .filter(this::isSubscribed)
                .filter(this::isExpired)
                .peek(user -> log.info("Subscription expired: {}", user.getId()))
                .forEach(this::unsubscribe);
    }
}
