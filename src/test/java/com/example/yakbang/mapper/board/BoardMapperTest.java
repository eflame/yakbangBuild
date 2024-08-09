package com.example.yakbang.mapper.board;

import com.example.yakbang.dto.board.BoardQnaDetailDTO;
import com.example.yakbang.dto.board.BoardQnaListDTO;
import com.example.yakbang.dto.board.BoardQnaWriteDTO;
import com.example.yakbang.service.board.BoardService;
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

}