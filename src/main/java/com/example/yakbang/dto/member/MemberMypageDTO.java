package com.example.yakbang.dto.member;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor @Builder
public class MemberMypageDTO {
    //마이페이지
    private Long memberId;
    private String loginId;
    private String name;
    private String email;
    private String birth;
    private String phoneNumber;
}
