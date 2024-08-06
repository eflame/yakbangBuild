package com.example.yakbang.dto.member;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor @Builder
public class ExpertMemberJoinDTO {
    private Long memberId;
    private String loginId;
    private String password;
    private String phoneNumber;
    private String name;
    private String birth;
    private String email;
    private String gender;
    private String job;
    private String pharmacyAddress;
}
