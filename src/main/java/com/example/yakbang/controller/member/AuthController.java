package com.example.yakbang.controller.member;

import com.example.yakbang.dto.member.KakaoInfoDTO;
import com.example.yakbang.service.member.AuthService;
import com.example.yakbang.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MemberService memberService;

    @GetMapping("/auth/kakao/login")
    public String kakaoLogin() {
        String location = authService.getKakaoLoginURI();
        return "redirect:" + location; // "redirect:" 접두사 추가
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(@RequestParam String code, HttpSession session) {
        System.out.println("code = " + code);

        KakaoInfoDTO kakaoInfo = authService.getMemberIdByKakaoInfo(code);
        Long kakaoId = kakaoInfo.getKakaoId();
        String nickName = kakaoInfo.getNickName();
        Long memberId = kakaoInfo.getMemberId();
        String memberType = kakaoInfo.getMemberType();

        
        if (memberId != null ) {
            session.setAttribute("memberId", memberId);
            session.setAttribute("memberType", memberType);
            return "redirect:/";
        } else {
            session.setAttribute("kakaoId", kakaoId);
            session.setAttribute("nickName", nickName);
            return "redirect:/member/join";
        }
    }
}

