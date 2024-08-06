package com.example.yakbang.dto.member;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor @Builder
public class MemberDTO {
    //modify페이지와 아이디 비번 찾기페이지용 DTO
    private String loiginId;
    private String password;
    private String name;
    private String email;
    private String birth;
    private String phoneNumber;
}
