package com.example.yakbang.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/find_id")
    public String findId() {
        return "member/find_id";
    }

    @GetMapping("/find_password")
    public String findPassword() {
        return "member/find_password";
    }

    @GetMapping("/find_id_email")
    public String findIdEmail() {
        return "member/find_id_email";
    }

    @GetMapping("/join")
    public String join() {
        return "member/join";
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "member/mypage";
    }

    @GetMapping("/mypage-modify")
    public String mypageModify() {
        return "member/mypage-modify";
    }


}