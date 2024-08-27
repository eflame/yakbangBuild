package com.example.yakbang.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class RecaptchaVerificationService {

    @Value("${recaptcha.secret.key}")
    private String apiKey;

    private static final String RECAPTCHA_VERIFY_URL = "https://recaptchaenterprise.googleapis.com/v1/projects/yakbang-1724618755593/assessments?key=";

    public boolean verifyRecaptcha(String token, String expectedAction) throws IOException {
        // JSON 파일 작성
        File jsonFile = new File("request.json");
        String jsonBody = "{\n" +
                "  \"event\": {\n" +
                "    \"token\": \"" + token + "\",\n" +
                "    \"expectedAction\": \"" + expectedAction + "\",\n" +
                "    \"siteKey\": \"6LflFS8qAAAAAKV_ANEzLehO8asihLHfcO41z1wO\"\n" +
                "  }\n" +
                "}";

        Files.write(jsonFile.toPath(), jsonBody.getBytes());

        // HTTP POST 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
        String url = RECAPTCHA_VERIFY_URL + apiKey;

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

        // 응답 처리
        Map<String, Object> responseBody = response.getBody();
        return responseBody != null && (Boolean) responseBody.get("success");
    }
}
