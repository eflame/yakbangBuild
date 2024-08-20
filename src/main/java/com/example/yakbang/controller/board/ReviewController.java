package com.example.yakbang.controller.board;

import com.example.yakbang.dto.board.ReviewListDTO;
import com.example.yakbang.dto.board.ReviewWriteDTO;
import com.example.yakbang.dto.pill.PageRequest;
import com.example.yakbang.service.board.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/write")
    public String reviewWrite(@SessionAttribute(value = "memberId", required = false) Long memberId){
        if(memberId == null){
            return "redirect:/member/login";
        }

        return "board/write";
    }

    @PostMapping("/write")
    public String reviewWrite(ReviewWriteDTO reviewWriteDTO,
                              @SessionAttribute("memberId") Long memberId){
        reviewWriteDTO.setMemberId(memberId);
        log.info("reviewWriteDTO : {}", reviewWriteDTO);
        reviewService.addReview(reviewWriteDTO);

        return "redirect:/review/list";
    }

    @GetMapping("/list")
    public String reviewList(String keyword, PageRequest pageRequest, Model model){
        List<ReviewListDTO> list = reviewService.findListWithPage(keyword, pageRequest);
        model.addAttribute("list", list);

        return "board/review_list";
    }
}














