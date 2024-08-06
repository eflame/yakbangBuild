package com.example.yakbang.dto.member;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDTO {

  private Long memberId;
  private String loginId;
  private String password;
  private String phoneNumber;
  private String name;
  private LocalDate birth;
  private String email;
  private String gender;

}
