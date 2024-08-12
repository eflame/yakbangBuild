package com.example.yakbang.mapper.board;

import com.example.yakbang.dto.board.BoardQnaDetailDTO;
import com.example.yakbang.dto.board.BoardQnaListDTO;
import com.example.yakbang.dto.board.BoardQnaWriteDTO;
import com.example.yakbang.service.board.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardMapperTest {

    @Autowired BoardMapper boardMapper;

    BoardQnaWriteDTO boardQnaWriteDTO;

    @Test
    @DisplayName("게시물 생성")
    void insertBoardQuestion() {
        boardQnaWriteDTO = new BoardQnaWriteDTO();
        boardQnaWriteDTO.setPillId(1L);
        boardQnaWriteDTO.setTitle("title");
        boardQnaWriteDTO.setContent("content");
        boardQnaWriteDTO.setMemberId(46L); // 실제 데이터베이스에 존재하는 값 사용

        boardMapper.insertBoardQuestion(boardQnaWriteDTO);

        BoardQnaDetailDTO boardQnaDetailDTO = boardMapper.selectQuestionDetail(boardQnaWriteDTO.getQuestionId()).get();
        System.out.println("boardMapper = " + boardMapper);

        assertThat(boardQnaDetailDTO.getTitle())
                .isEqualTo(boardQnaWriteDTO.getTitle());

        assertThat(boardQnaDetailDTO)
                .extracting("title")
                .isEqualTo(boardQnaWriteDTO.getTitle());

        assertThat(boardQnaDetailDTO)
                .extracting("title", "content")
                .containsExactly(boardQnaWriteDTO.getTitle(), boardQnaWriteDTO.getContent());
    }
    }
