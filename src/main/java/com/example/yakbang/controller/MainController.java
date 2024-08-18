package com.example.yakbang.controller;

import com.example.yakbang.dto.board.BoardQnaListDTO;
import com.example.yakbang.dto.board.BoardSearchDTO;
import com.example.yakbang.service.board.BoardService;
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

    @GetMapping("/")
    public String index(Model model) {
        List<BoardQnaListDTO> list = boardService.findList(); // qna 게시물 목록 호출
        List<BoardQnaListDTO> limitedList = list.stream()     // qna 게시물 목록 4가지로 제한
                .limit(4)
                .collect(Collectors.toList());
        model.addAttribute("list", limitedList);

        return "main";
    }



}
