package com.example.yakbang.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class LoginController {

  @GetMapping("/login")
  public String login() {
    return "member/login";
  }

  @PostMapping("/login")
  public String login(@RequestParam String loginId, @RequestParam String password) {



  }

  @GetMapping("/find_password")
  public String findPassword() {
    return "member/find_password";
  }

  @GetMapping("/find_id")
  public String findId() {
    return "member/find_id";
  }

}
