package com.example.yakbang.controller.board;

import com.example.yakbang.dto.board.*;
import com.example.yakbang.service.board.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/qna-list")
    public String qna_list(BoardSearchDTO boardSearchDTO, Model model) {
        System.out.println("boardSearchDTO = " + boardSearchDTO);
//        List<BoardQnaListDTO> list = boardService.findList(); // 게시물 목록 호출
        List<BoardQnaListDTO> list = boardService.findSearchList(boardSearchDTO);

        model.addAttribute("list", list);

        return "board/qna_list";
    }

    @GetMapping("/qna-detail")
    public String qna_detail(@RequestParam("questionId") Long questionId, Model model) {
        System.out.println("questionId = " + questionId);
        BoardQnaDetailDTO detail = boardService.findDetail(questionId);
        model.addAttribute("detail", detail);

        return "board/qna_detail";
    }

    @GetMapping("/qna-write")
    public String qna_write(HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");

        if (memberId == null) {   // 회원이 아니면 로그인으로
            return "redirect:/member/login";
        }
        return "board/qna_write"; // 회원이라면 작성페이지로
    }

    @PostMapping("/qna-write")
    public String qna_write(BoardQnaWriteDTO boardQnaWriteDTO,
                            @SessionAttribute("memberId") Long memberId) {
        boardQnaWriteDTO.setMemberId(memberId);
        log.info("boardQnaWriteDTO:{}", boardQnaWriteDTO); // 로그 기록

        boardService.addBoard(boardQnaWriteDTO);

        return "redirect:/board/qna-list";
    }

    @GetMapping("/answer")
    public String answer(@ModelAttribute("questionId") Long questionId, HttpSession session) {
        session.getAttribute("memberType");
        Long expertId = (Long) session.getAttribute("memberId");

        if (expertId == null) {   // 전문가가 아니면 로그인으로
            return "redirect:/member/login";
        }

        return "board/answer"; // 전문가라면 답변페이지로
    }

    @PostMapping("/answer")
    public String answer(AnswerWriteDTO answerWriteDTO,
                         @SessionAttribute("memberId") Long memberId,
                         @SessionAttribute("memberType") String memberType) {

        log.info("answerWriteDTO = {} ", answerWriteDTO);
        log.info("memberId = {} ", memberId);
        log.info("memberType = {} ", memberType);

        if ("expert".equals(memberType)) {
            answerWriteDTO.setExpertId(memberId);
            boardService.addAnswer(answerWriteDTO);
        }


        return "redirect:/board/qna-list";
    }

}
