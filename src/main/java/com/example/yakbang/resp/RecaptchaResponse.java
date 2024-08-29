package com.example.yakbang.resp;

import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class RecaptchaResponse {
    private boolean success;
    private String challengeTs;
    private String hostname;
    private List<String> errorCodes;
}
