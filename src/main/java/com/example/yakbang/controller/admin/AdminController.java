package com.example.yakbang.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/login")
    public String login() {
        return "admin/login";
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
