package com.example.yakbang.resp;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class RecaptchaResponse {
    private boolean success;
    @Value("challenge_ts")
    private String  challengeTs;
    private String hostname;
}
