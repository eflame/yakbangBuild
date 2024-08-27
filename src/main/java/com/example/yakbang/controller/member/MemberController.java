package com.example.yakbang.controller.member;

import com.example.yakbang.dto.member.ExpertMypageDTO;
import com.example.yakbang.dto.member.MemberJoinDTO;
import com.example.yakbang.dto.member.MemberModifyDTO;
import com.example.yakbang.dto.member.MemberMypageDTO;
import com.example.yakbang.service.member.AuthService;
import com.example.yakbang.service.member.ExpertService;
import com.example.yakbang.service.member.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String login() {
        return "member/login";
    }




    @PostMapping("/login")
    public String login(String loginId, String password, String memberType,
                        HttpSession session) {
        Long memberId = null;

        try {
            if ("general".equals(memberType)) {
                memberId = memberService.findMemberId(loginId, password);
            } else {
                memberId = expertService.findExpertId(loginId, password);
            }
        } catch (IllegalArgumentException e) {
            log.error(e.toString());
            return "member/login";
        } catch (Exception e) {
            log.error(e.toString());
            return "member/login";
        }

        // 로그인 성공 시 세션 처리
        session.setAttribute("memberId", memberId);
        return "redirect:/";
    }

    @GetMapping("/find_id")
    public String findId() {
        return "member/find_id";
    }

    @PostMapping("/find_id")
    public String findId(String name, String email, HttpSession session, Model model) {
        String loginId = null;

        try {
            // MemberService와 ExpertService의 공통 로직을 처리하는 통합 서비스 호출
            loginId = findLoginId(name, email);
        } catch (Exception e) {
            log.error("ID 찾기 실패: ", e);
            model.addAttribute("errorMessage", "ID를 찾는 데 문제가 발생했습니다.");
            return "member/find-id";  // 실패 페이지로 리턴
        }

        if (loginId == null) {
            model.addAttribute("errorMessage", "해당 정보와 일치하는 ID가 없습니다.");
            return "member/find-id";  // 실패 페이지로 리턴
        }

        model.addAttribute("loginId", loginId);
        return "member/find-result";  // 성공 페이지로 리턴
    }

    /**
     * ID를 찾는 공통 로직을 처리하는 메소드
     */
    private String findLoginId(String name, String email) throws Exception {
        String loginId = memberService.findLoginId(name, email);
        if (loginId == null) {
            loginId = expertService.findExpertLoginId(name, email);
        }
        return loginId;
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
    public String join(@ModelAttribute("memberJoinDTO") MemberJoinDTO memberJoinDTO,Model model,HttpSession session) {
        String kakaoId = session.getAttribute("kakaoId").toString();
        System.out.println("kakaoId = " + kakaoId);
        String nickname = session.getAttribute("nickName").toString();
        System.out.println("nickname = " + nickname);
        model.addAttribute("kakaoId", kakaoId);
        model.addAttribute("nickname", nickname);
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

        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {
        Long memberId = (Long) session.getAttribute("memberId");
        String memberType= (String) session.getAttribute("memberType");
        System.out.println("memberType = " + memberType);
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