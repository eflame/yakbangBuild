package com.example.yakbang.dto.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AdminMemberDTO {
    private Long memberId;
    private String loginId;
    private String phoneNumber;
    private String name;
    private String birth;
    private String email;
    private String gender;
}
