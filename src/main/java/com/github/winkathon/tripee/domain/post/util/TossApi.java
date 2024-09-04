package com.github.winkathon.tripee.domain.post.util;

import java.util.Base64;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.github.winkathon.tripee.common.property.TossProperty;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TossApi {

    private final RestTemplate restTemplate = new RestTemplate();

    private final TossProperty tossProperty;

    private HttpHeaders headers;

    @PostConstruct
    public void init() {

        String basicRaw = "%s:".formatted(tossProperty.getKey());
        String basic = Base64.getEncoder().encodeToString(basicRaw.getBytes());

        headers = new HttpHeaders();
        headers.set("Authorization", "Basic %s".formatted(basic));
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public void pay(String orderId, String paymentKey, int amount) {

        Map<String, ?> body = Map.ofEntries(
                Map.entry("paymentKey", paymentKey),
                Map.entry("orderId", orderId),
                Map.entry("amount", amount)
        );

        try {
            //noinspection rawtypes
            ResponseEntity<Map> response = restTemplate.exchange(
                    "https://api.tosspayments.com/v1//payments/confirm",
                    HttpMethod.POST,
                    new HttpEntity<>(body, headers),
                    Map.class
            );

            assert response.getBody() != null;
            if (!response.getBody().get("status").equals("DONE")) {

                throw new RuntimeException("결제 실패");
            }
        } catch (HttpClientErrorException.NotFound e) {

            throw new RuntimeException("결제 실패");
        } catch (HttpClientErrorException e) {

            log.warn("Toss API 호출 중 오류가 발생했습니다.", e);

            throw new RuntimeException("결제 실패");
        }
    }
}
