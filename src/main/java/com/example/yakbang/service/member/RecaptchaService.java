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

    private static final String RECAPTCHA_SECRET_KEY = "6LflFS8qAAAAAGv3Ga07w5xU4UxwQv4jRa9RTmVr";
    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public boolean verifyRecaptcha(String userResponse) {
        RestTemplate restTemplate = new RestTemplate();

        String params = "?secret=" + RECAPTCHA_SECRET_KEY + "&response=" + userResponse;
        String verifyUrl = RECAPTCHA_VERIFY_URL + params;

        String response = restTemplate.postForObject(verifyUrl, null, String.class);
        JSONObject jsonResponse = new JSONObject(response);

        return jsonResponse.getBoolean("success");
    }
}

