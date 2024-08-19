package com.example.yakbang.controller.member;

import com.example.yakbang.dto.member.ExpertMypageDTO;
import com.example.yakbang.dto.member.MemberJoinDTO;
import com.example.yakbang.dto.member.MemberModifyDTO;
import com.example.yakbang.dto.member.MemberMypageDTO;
import com.example.yakbang.service.member.ExpertService;
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
    private final ExpertService expertService;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(String loginId, String password,String memberType,
                        HttpSession session) {
        Long memberId = null;

        System.out.println("loginId = " + loginId + ", password = " + password + ", memberType = " + memberType + ", session = " + session);
        try {
            if("general".equals(memberType)){
                memberId = memberService.findMemberId(loginId, password);
            }else {
                memberId = expertService.findExpertId(loginId, password);
            }

//
        } catch (IllegalArgumentException e) {
            log.error(e.toString());
            return "member/login";
        } catch (Exception e) {
            log.error(e.toString());
            return "member/login";
        }

        session.setAttribute("memberId", memberId);
        session.setAttribute("memberType", memberType);
// 로그인 되면 memberId를 세션으로 설정해 두었기에 mypage에서 정보수정시 해당 데이터를 가져다가 쓸것임.


        return "/main";
    }

    @GetMapping("/find_id")
    public String findId() {
        return "member/find_id";
    }

    @PostMapping("/find_id")
    public String findId(String name,String email,
                         HttpSession session,Model model) {

//        Long memberId = (Long) session.getAttribute("memberId");
//        String memberType= (String) session.getAttribute("memberType");

        String loginId = null;
        try {
            loginId = memberService.findLoginId(name, email);
        } catch (Exception e) {
            try {
                loginId = expertService.findExpertLoginId(name, email);
            } catch (Exception ex) {
                loginId = "존재하지 않음";
            }
        }
        model.addAttribute("loginId", loginId);

        return "member/find-result";
    }

    @GetMapping("/find_password")
    public String findPassword() {
        return "member/find_password";
    }

    @PostMapping("/find_password")
    public String findPassword(String email,String loginId,
                               HttpSession session,Model model) {
        String password=null;
        try {
            password = memberService.findPassword(loginId, email);
        } catch (Exception e) {
            try {
                password = expertService.findExpertPassword(loginId, email);
            } catch (Exception ex) {
                password = "존재하지 않음";
            }
        }
        model.addAttribute("password", password);

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
    public String join(MemberJoinDTO memberJoinDTO, Model model,boolean check ) {
        try {
            if (check){
                expertService.addExpert(memberJoinDTO);
            }else {
                memberService.addMember(memberJoinDTO);
            }
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
        String memberType= (String) session.getAttribute("memberType");

        if ("general".equals(memberType)) {
            MemberMypageDTO memberDTO = memberService.searchMember(memberId);
            model.addAttribute("memberDTO", memberDTO);
        }else{
            ExpertMypageDTO expertMypageDTO = expertService.searchMember(memberId);
            log.info("////////////////////////////");
            model.addAttribute("memberType", memberType);
            model.addAttribute("memberDTO", expertMypageDTO);
        }


        return "member/mypage";
    }

    @GetMapping("/mypage-modify")
    public String mypageModify(Model model, HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        String memberType= (String) session.getAttribute("memberType");

        if ("general".equals(memberType)) {
            MemberMypageDTO memberDTO = memberService.searchMember(memberId);
            model.addAttribute("memberDTO", memberDTO);
        }else{
            ExpertMypageDTO expertMypageDTO = expertService.searchMember(memberId);
            model.addAttribute("memberType", memberType);
            model.addAttribute("memberDTO", expertMypageDTO);
        }

        return "member/mypage-modify";
    }

    @PostMapping("/mypage-modify")
    public String mypageModify(MemberModifyDTO memberModifyDTO, HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        String memberType= (String) session.getAttribute("memberType");
        if ("general".equals(memberType)){
            memberModifyDTO.setMemberId(memberId);
            memberService.modifyMemberInfo(memberModifyDTO);
        }else{
            memberModifyDTO.setExpertId(memberId);
            expertService.modifyExpertInfo(memberModifyDTO);
        }



        return "redirect:/member/mypage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/member/login";
    }

    @GetMapping("/leave")
    public String leave(HttpSession session){
        Long memberId = (Long) session.getAttribute("memberId");
        String memberType= (String) session.getAttribute("memberType");

        if ("general".equals(memberType)) {
            memberService.removeMemberInfo(memberId);
        }else{
            expertService.removeExpertInfo(memberId);
        }
        session.invalidate();
        return "/main";
    }

}