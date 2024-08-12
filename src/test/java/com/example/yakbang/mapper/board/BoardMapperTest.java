package com.example.yakbang.mapper.board;

import com.example.yakbang.dto.board.BoardQnaDetailDTO;
import com.example.yakbang.dto.board.BoardQnaListDTO;
import com.example.yakbang.dto.board.BoardQnaWriteDTO;
import com.example.yakbang.service.board.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    BoardQnaListDTO boardQnaListDTO;

    @BeforeEach
    void setUp(){
        boardQnaWriteDTO = BoardQnaWriteDTO.builder()
                .memberId(46L)
                .pillId(1L)
                .title("제목 test")
                .content("내용 test")
                .build();
    }

    @Test
    @DisplayName("게시물 생성")
    void insertBoardQuestion() {
        boardQnaWriteDTO = new BoardQnaWriteDTO();
        boardQnaWriteDTO.setTitle("Sample Title");
        boardQnaWriteDTO.setContent("Sample Content");
        boardQnaWriteDTO.setMemberId(46L); // 실제 데이터베이스에 존재하는 값 사용

        // When: 게시물 삽입
        boardMapper.insertBoardQuestion(boardQnaWriteDTO);

        System.out.println("boardMapper = " + boardMapper);
    }

    @Test
    void selectQuestionList() {
        //g
        boardMapper.insertBoardQuestion(boardQnaWriteDTO);
        //w
        List<BoardQnaListDTO> list = boardMapper.selectQuestionList();
        //t
        assertThat(list).hasSize(1);
    }

    @Test
    void selectQuestionDetail() {
    }
}