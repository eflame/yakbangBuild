package com.example.yakbang.dto.member;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor @Builder
public class MemberModifyDTO {
    //마이페이지
    private Long memberId;
    private String name;
    private String email;
    private String birth;
    private String phoneNumber;
    private Long expertId;
    private String job;
    private String pharmacyAddress;
}
