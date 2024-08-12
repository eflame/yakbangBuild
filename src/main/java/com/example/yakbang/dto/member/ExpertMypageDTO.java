package com.example.yakbang.dto.member;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor @Builder
public class ExpertMypageDTO {
    //마이페이지
    private Long expertId;
    private String loginId;
    private String name;
    private String email;
    private String birth;
    private String phoneNumber;
    private String job;
    private String pharmacyAddress;
}
