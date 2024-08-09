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
    public String login(HttpSession session, Model model) {

        String loginId = (String) session.getAttribute("loginId");
        if (loginId != null) {
            return "redirect:/admin/index";
        }
        return "admin/login";
    }

    @PostMapping("")
    public String login(@RequestParam String loginId,
                        @RequestParam String password,
                        @RequestParam(required = false) String rememberMe,
                        HttpSession session,
                        HttpServletResponse response,
                        RedirectAttributes redirectAttributes,
                        Model model) {
        Long adminId = null;


        try {
            adminId = adminService.findAdminId(loginId, password);

            session.setAttribute("loginId", loginId);

            if ("on".equals(rememberMe)) {
                Cookie loginIdCookie = new Cookie("loginId", loginId);
                loginIdCookie.setMaxAge(7 * 24 * 60 * 60); // 7일
                loginIdCookie.setPath("/");
                response.addCookie(loginIdCookie);
            } else {
                // Remember Me가 선택되지 않았을 경우 기존 쿠키 삭제
                Cookie loginIdCookie = new Cookie("loginId", null);
                loginIdCookie.setMaxAge(0); // 즉시 만료
                loginIdCookie.setPath("/");
                response.addCookie(loginIdCookie);
            }

            return "redirect:/admin/index";

        } catch (IllegalArgumentException e) {
            log.error(e.toString());
            model.addAttribute("msg", "로그인 실패: 사용자 이름이나 비밀번호가 올바르지 않습니다.");
            return "admin/login";
        } catch (Exception e) {
            log.error(e.toString());
            model.addAttribute("msg", "로그인 중 오류가 발생했습니다. 다시 시도해 주세요.");
            return "admin/login";
        }
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
