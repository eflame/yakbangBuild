package com.example.yakbang.controller.admin;

import com.example.yakbang.service.admin.AdminService;
import com.example.yakbang.service.member.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.NoSuchElementException;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("")
    public String login(HttpSession session,
                        HttpServletRequest request,
                        Model model) {
        Long memberId = (Long) session.getAttribute("memberId");

        if (memberId != null) {
            // 로그인된 상태인 경우, 인덱스 페이지로 리디렉션
            return "redirect:/admin/index";
        }

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
        return "admin/login";
    }

    @PostMapping("")
    public String login(@RequestParam String loginId,
                        @RequestParam String password,
                        @RequestParam(required = false) String rememberLoginId,
                        HttpSession session,
                        HttpServletResponse response,
                        Model model) {
        // 현재 세션에서 memberId를 확인하여 로그인 상태를 체크
        Long memberId = null;


        try {
            // 관리자 ID와 비밀번호로 로그인 시도
            memberId = adminService.findAdminId(loginId, password);
            if (memberId != null) {
                // 로그인 성공 시 세션에 로그인 ID 및 memberId 저장
                session.setAttribute("loginId", loginId);
                session.setAttribute("memberId", memberId);

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

                // 로그인 성공 후 인덱스 페이지로 리디렉션
                return "redirect:/admin/index";
            } else {
                // 로그인 실패 시 메시지 설정
                model.addAttribute("msg", "로그인 실패: 관리자 ID나 비밀번호가 올바르지 않습니다.");
                return "admin/login";
            }

        } catch (Exception e) {
            // 예외 발생 시 메시지 설정
            model.addAttribute("msg", "로그인 처리 중 오류가 발생했습니다.");
            return "admin/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        if (session != null) {
            session.invalidate();
        }

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

        return "redirect:/admin";
    }

    @GetMapping("/pill")
    public String pill() {
        return "admin/pill";
    }

    @GetMapping("/qna")
    public String qnaBoard() {
        return "admin/qna_board";
    }

    @GetMapping("/review")
    public String review() {
        return "admin/review_board";
    }


}
