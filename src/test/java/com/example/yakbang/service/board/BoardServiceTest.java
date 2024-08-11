package com.example.yakbang.service.board;

import com.example.yakbang.dto.board.BoardQnaDetailDTO;
import com.example.yakbang.dto.board.BoardQnaWriteDTO;
import com.example.yakbang.mapper.board.BoardMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testAddBoard() {
        BoardQnaWriteDTO dto = BoardQnaWriteDTO.builder()
                .title("Test Title")
                .content("Test Content")
                .memberId(46L)
                .build();
        boardMapper.insertBoardQuestion(dto);

        BoardQnaDetailDTO savedDetail = boardMapper.selectQuestionDetail(dto.getQuestionId())
                .orElse(null); // 질문 ID에 해당하는 질문이 존재하지 않을 경우 null을 반환합니다.

        // 데이터베이스에서 조회한 질문의 세부 정보가 null이 아닌지 확인합니다.
        assertThat(savedDetail).isNotNull();
    }
}