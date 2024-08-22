package com.example.yakbang.controller.member;

import com.example.yakbang.dto.member.ExpertMypageDTO;
import com.example.yakbang.dto.member.MemberJoinDTO;
import com.example.yakbang.dto.member.MemberModifyDTO;
import com.example.yakbang.dto.member.MemberMypageDTO;
import com.example.yakbang.service.member.ExpertService;
import com.example.yakbang.service.member.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final ExpertService expertService;

    @GetMapping("/login")
    public String login(HttpServletRequest request,
                        Model model) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("loginId".equals(cookie.getName())) {
                    String loginId = cookie.getValue();
                    model.addAttribute("loginId", loginId);
                    break;
                }
            }
        }
        return "member/login";
    }

    @PostMapping("/login")
    public String login(String loginId, String password, String memberType,
                        @RequestParam(required = false) String rememberLoginId,
                        HttpSession session, HttpServletResponse response) {
        Long memberId = null;

        System.out.println("loginId = " + loginId + ", password = " + password + ", memberType = " + memberType + ", session = " + session);
        try {
            if("general".equals(memberType)){
                memberId = memberService.findMemberId(loginId, password);
            }else {
                memberId = expertService.findExpertId(loginId, password);
            }

            if (memberId != null) {
                // 로그인 성공 시 세션에 로그인 ID 및 memberId 저장
                session.setAttribute("loginId", loginId);
                session.setAttribute("memberId", memberId);
                session.setAttribute("memberType", memberType);
                // "로그인 ID 저장" 체크 여부에 따라 쿠키 설정
                if ("on".equals(rememberLoginId)) {
                    Cookie loginIdCookie = new Cookie("loginId", loginId);
                    loginIdCookie.setMaxAge(30 * 24 * 60 * 60); // 30일간 유지
                    loginIdCookie.setPath("/");
                    response.addCookie(loginIdCookie);
                } else {
                    // 체크하지 않으면 쿠키 삭제
                    Cookie loginIdCookie = new Cookie("loginId", null);
                    loginIdCookie.setMaxAge(0); // 즉시 만료
                    loginIdCookie.setPath("/");
                    response.addCookie(loginIdCookie);
                }

                return "redirect:/";
            } else {
                log.info("로그인 실패: ID나 비밀번호가 올바르지 않습니다.");
                return "member/login";
            }

//
        } catch (IllegalArgumentException e) {
            log.error(e.toString());
            return "member/login";
        } catch (Exception e) {
            log.error(e.toString());
            return "member/login";
        }
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
        }else if ("expert".equals(memberType)) {
            ExpertMypageDTO expertMemberDTO = expertService.searchMember(memberId);
            log.info("////////////////////////////");
            model.addAttribute("memberType", memberType);
            model.addAttribute("memberDTO", expertMemberDTO);
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
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        session.invalidate();
        // 로그인 ID 쿠키는 유지하고, 다른 관련 쿠키는 삭제하거나 무효화
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (!"loginId".equals(cookie.getName())) { // loginId는 유지
                    cookie.setValue(null);
                    cookie.setMaxAge(0); // 즉시 만료
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
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