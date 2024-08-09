package com.example.yakbang.dto.admin;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class AdminDTO {
    private Long memberId;
    private String loginId;
    private String password;
    private boolean rememberMe;
}
