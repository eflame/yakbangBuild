package com.example.yakbang.controller.member;

import com.example.yakbang.dto.member.MemberJoinDTO;
import com.example.yakbang.dto.member.MemberMypageDTO;
import com.example.yakbang.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(String loginId, String password,
                        HttpSession session) {
        Long memberId = null;

        try {
            memberId = memberService.findMemberId(loginId, password);
        } catch (IllegalArgumentException e) {
            log.error(e.toString());
            return "member/login";
        } catch (Exception e) {
            log.error(e.toString());
            return "member/login";
        }

        session.setAttribute("memberId", memberId);
// 로그인 되면 memberId를 세션으로 설정해 두었기에 mypage에서 정보수정시 해당 데이터를 가져다가 쓸것임.


        return "/main";
    }

    @GetMapping("/find_id")
    public String findId() {
        return "member/find_id";
    }

    @PostMapping("/find_id")
    public String findId(String name,String email,
                         HttpSession session) {
        String loginId = memberService.findLoginId(name, email);
        session.getAttribute("memberId");
        return "member/find-result";
    }

    @GetMapping("/find_password")
    public String findPassword() {
        return "member/find_password";
    }

    @PostMapping("/find_password")
    public String findPassword(String email,String loginId,
                               HttpSession session) {
        return "member/find-result2";
    }

    @GetMapping("/find_id_email")
    public String findIdEmail() {
        return "member/find_id_email";
    }

    @GetMapping("/join")
    public String join(@ModelAttribute("memberJoinDTO") MemberJoinDTO memberJoinDTO) {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(MemberJoinDTO memberJoinDTO, Model model) {
        try {

            memberService.addMember(memberJoinDTO);
        } catch (IllegalStateException e) {
            log.error(e.toString());
            model.addAttribute("memberJoinDTO", memberJoinDTO);
            model.addAttribute("duplicate", true);
            return "member/join";
        }

        return "/main";
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {
        Long memberId = (Long) session.getAttribute("memberId");
        MemberMypageDTO memberDTO = memberService.searchMember(memberId);
        model.addAttribute("memberDTO", memberDTO);
        return "member/mypage";
    }

    @GetMapping("/mypage-modify")
    public String mypageModify(Model model, HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        MemberMypageDTO memberDTO = memberService.searchMember(memberId);
        model.addAttribute("memberDTO", memberDTO);

        return "member/mypage-modify";
    }

    @PostMapping("/mypage-modify")
    public String mypageModify(MemberMypageDTO memberMypageDTO) {
        memberService.modifyMemberInfo(memberMypageDTO);



        return "redirect:/member/mypage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/member/login";
    }

}