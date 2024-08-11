package com.example.yakbang.dto.admin;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class AdminExMemberDTO {
    private Long expertId;
    private String loginId;
    private String name;
    private String gender;
    private String birth;
    private String phoneNumber;
    private String email;
    private String job;
    private String pharmacyAddress;

}
