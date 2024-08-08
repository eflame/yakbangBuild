package com.example.yakbang.mapper.board;

import com.example.yakbang.dto.board.BoardQnaListDTO;
import com.example.yakbang.dto.board.BoardQnaWriteDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardMapperTest {

    @Autowired BoardMapper boardMapper;
    BoardQnaWriteDTO boardQnaWriteDTO;

        @Test
        void setUp() {
            boardQnaWriteDTO = BoardQnaWriteDTO.builder()
                    .title("test title")
                    .content("test content")
                    .memberId(1L)
                    .build();
        }

    @Test
    void insertBoardQuestion() {
        // given
        boardMapper.insertBoardQuestion(boardQnaWriteDTO);
        // when
        List<BoardQnaListDTO> memberId = boardMapper.selectQuestionList();
        // then
        assertThat(memberId).isEqualTo(boardQnaWriteDTO.getMemberId());

    }

    @Test
    void selectQuestionList() {
    }
}