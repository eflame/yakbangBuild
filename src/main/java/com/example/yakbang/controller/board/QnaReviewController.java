package com.example.yakbang.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class QnaReviewController {
    @GetMapping("/qna-list")
    public String qna_list() {
        return "board/qna_list";
    }

    @GetMapping("/qna-detail")
    public String qna_detail() {
        return "board/qna_detail";
    }

    @GetMapping("/review-list")
    public String list() {
        return "board/review_list";
    }

    @GetMapping("/review-write")
    public String write() {
        return "board/write";
    }
    @GetMapping("/qna-write")
    public String qna_write() {
        return "board/qna_write";
    }

}
