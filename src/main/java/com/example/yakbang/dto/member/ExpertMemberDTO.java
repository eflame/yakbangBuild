package com.example.yakbang.dto.member;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpertMemberDTO {

  private Long expertId;
  private String loginId;
  private String password;
  private String phoneNumber;
  private String name;
  private LocalDate birth;
  private String email;
  private String gender;
//  private String job;
//  private String pharmacyAddress;
}
