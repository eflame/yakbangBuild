package com.example.yakbang.api.board;

import com.example.yakbang.dto.board.BoardQnaListDTO;
import com.example.yakbang.dto.pill.PageRequest;
import com.example.yakbang.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class QuestionApi {
    private final BoardService boardService;

    @GetMapping("/v1/question")
    public List<BoardQnaListDTO> getQuestionList(String keyword, PageRequest pageRequest){
        return boardService.findListWithPage(keyword, pageRequest);
    }
}
