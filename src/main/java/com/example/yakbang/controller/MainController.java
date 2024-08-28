package com.example.yakbang.controller;

import com.example.yakbang.dto.board.BoardQnaListDTO;
import com.example.yakbang.dto.board.ReviewListDTO;
import com.example.yakbang.service.board.BoardService;
import com.example.yakbang.service.board.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final BoardService boardService;
    private final ReviewService reviewService;

    @GetMapping("/")
    public String index(String keyword, Model model) {
        List<BoardQnaListDTO> list = boardService.findList(); // qna 게시물 목록 호출
        List<BoardQnaListDTO> limitedList = list.stream()     // qna 게시물 목록 4가지로 제한
                .limit(4)
                .collect(Collectors.toList());
        model.addAttribute("list", limitedList);

        List<ReviewListDTO> list2 = reviewService.findList(keyword);
        List<ReviewListDTO> limitedList2 = list2.stream()     // review 게시물 목록 8가지로 제한
                .limit(8)
                .toList();
        model.addAttribute("list2", limitedList2);

        return "main";
    }



}
