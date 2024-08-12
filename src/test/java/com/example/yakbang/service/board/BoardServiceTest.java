package com.example.yakbang.service.board;

import com.example.yakbang.dto.board.BoardQnaDetailDTO;
import com.example.yakbang.dto.board.BoardQnaWriteDTO;
import com.example.yakbang.mapper.board.BoardMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @Mock
    BoardMapper boardMapper;

    @InjectMocks
    BoardService boardService;

    @Test
    public void testAddBoard() {
        BoardQnaWriteDTO dto = BoardQnaWriteDTO.builder()
                .pillId(121L)
                .title("제목제목제목")
                .content("내용내용내용")
                .memberId(46L)
                .build();
        boardMapper.insertBoardQuestion(dto);

        BoardQnaDetailDTO savedDetail = boardMapper.selectQuestionDetail(dto.getQuestionId())
                .orElse(null); // 질문 ID에 해당하는 질문이 존재하지 않을 경우 null을 반환.

        // 데이터베이스에서 조회한 질문의 세부 정보가 null이 아닌지 확인.
        assertThat(savedDetail).isNotNull();
    }

    @Test
    void findDetail() {
            // given
            BoardQnaDetailDTO dto = BoardQnaDetailDTO.builder()
                    .pillId(1L)
                    .content("test")
                    .title("test")
                    .build();

            doReturn(Optional.of(dto)).when(boardMapper).selectQuestionDetail(any());
            // when
            BoardQnaDetailDTO board = boardService.findDetail(1L);
            // then
            Assertions.assertThat(board.getTitle()).isEqualTo("test");
    }
}