package com.example.yakbang.service.member;

import com.example.yakbang.resp.RecaptchaResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class RecaptchaService {
    @Value("${recaptcha.secret.key}")
    private String recaptchaSecret; // Google reCAPTCHA 비밀 키

    public boolean verifyRecaptcha(String userResponse) {
        String url = "https://www.google.com/recaptcha/api/siteverify";

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("secret", recaptchaSecret);
        requestBody.put("response", userResponse);

        ResponseEntity<RecaptchaResponse> response = restTemplate.postForEntity(url, requestBody, RecaptchaResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            RecaptchaResponse recaptchaResponse = response.getBody();
            return recaptchaResponse != null && recaptchaResponse.isSuccess();
        }
        return false;
    }
}

