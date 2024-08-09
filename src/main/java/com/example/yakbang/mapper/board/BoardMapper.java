package com.example.yakbang.mapper.board;

import com.example.yakbang.dto.board.BoardQnaDetailDTO;
import com.example.yakbang.dto.board.BoardQnaListDTO;
import com.example.yakbang.dto.board.BoardQnaWriteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    void insertBoardQuestion(BoardQnaWriteDTO boardQnaWriteDTO);

    List<BoardQnaListDTO> selectQuestionList();

    Optional<BoardQnaDetailDTO> selectQuestionDetail(Long questionId);
}
