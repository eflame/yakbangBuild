package com.example.yakbang.service.member;

import com.example.yakbang.resp.RecaptchaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
@RequiredArgsConstructor
public class RecaptchaService {
    @Value("${recaptcha.secret.key}")
    private String secretKey;

    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public boolean verifyRecaptcha(String token) {
        WebClient webClient = WebClient.builder().baseUrl(RECAPTCHA_VERIFY_URL).build();
        RecaptchaResponse response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("secret", secretKey)
                        .queryParam("response", token)
                        .build())
                .retrieve()
                .bodyToMono(RecaptchaResponse.class)
                .block();
        System.out.println("response = " + response);
        return response.isSuccess();
    }
}

