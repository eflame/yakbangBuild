package com.example.yakbang.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/join_page")
    public String join() {
        return "member/join_page";
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
