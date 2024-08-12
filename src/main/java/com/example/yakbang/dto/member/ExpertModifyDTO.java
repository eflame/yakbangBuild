package com.example.yakbang.dto.member;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor @Builder
public class ExpertModifyDTO {
    //마이페이지
    private Long expertId;
    private String name;
    private String email;
    private String birth;
    private String phoneNumber;
}
