package com.example.yakbang.controller.board;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/qna-list")
    public String qna_list() {
        return "board/qna_list";
    }

    @GetMapping("/qna-detail")
    public String qna_detail() {
        return "board/qna_detail";

    }

    @GetMapping("/qna-write")
    public String qna_write(HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");

        if(memberId == null){   // 회원이 아니면 로그인으로 이동
            return "redirect:/member/login";
        }
        return "board/qna_write";
    }

    @GetMapping("/review-list")
    public String list() {
        return "board/review_list";
    }

    @GetMapping("/review-write")
    public String write() {
        return "board/write";
    }

}
