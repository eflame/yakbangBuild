package com.example.yakbang.mapper.board;

import com.example.yakbang.dto.board.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    void insertBoardQuestion(BoardQnaWriteDTO boardQnaWriteDTO);

    List<BoardQnaListDTO> selectQuestionList();

    Optional<BoardQnaDetailDTO> selectQuestionDetail(Long questionId);

    List<BoardQnaListDTO> selectBoardSearch(BoardSearchDTO boardSearchDTO);

    void updateViewCount(Long questionId); // 조회수 증가

    void insertAnswer(AnswerWriteDTO answerWriteDTO); // 답변 저장

}
