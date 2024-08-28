package com.example.yakbang.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

import org.springframework.http.MediaType;

@Service
@Transactional
@RequiredArgsConstructor
public class RecaptchaVerificationService {

    @Value("${recaptcha.site.key}")
    private String siteKey;

    private static final String RECAPTCHA_VERIFY_URL = "https://recaptchaenterprise.googleapis.com/v1/projects/{your-project-id}/assessments?key=";

    public boolean verifyRecaptcha(String token, String expectedAction) {
        WebClient wc = WebClient.builder()
                .baseUrl(RECAPTCHA_VERIFY_URL + siteKey)
                .build();

        Mono<Map> responseMono = wc.post()
                .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                .body(BodyInserters.fromValue(
                        Map.of(
                                "event", Map.of(
                                        "token", token,
                                        "expectedAction", expectedAction,
                                        "siteKey", siteKey
                                )
                        )
                ))
                .retrieve()
                .bodyToMono(Map.class);

        Map<String, Object> response = responseMono.block();

        if (response != null) {
            Boolean success = (Boolean) response.get("success");
            Double score = (Double) response.get("score");
            String action = (String) response.get("action");

            // 추가 검증 로직 (필요한 경우)
            return success != null && success && score != null && score >= 0.5 && expectedAction.equals(action);
        }

        return false;
    }
}
