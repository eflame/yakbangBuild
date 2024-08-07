package com.example.yakbang.dto.member;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor @Builder
public class MemberLoginDTO {
    private String loiginId;
    private String password;
}
