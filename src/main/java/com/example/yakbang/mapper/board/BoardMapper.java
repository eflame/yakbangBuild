package com.example.yakbang.mapper.board;

import com.example.yakbang.dto.board.BoardQnaListDTO;
import com.example.yakbang.dto.board.BoardQnaWriteDTO;
import com.example.yakbang.dto.member.MemberJoinDTO;
import com.example.yakbang.dto.member.MemberMypageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    void insertBoardQuestion(BoardQnaWriteDTO boardQnaWriteDTO);

    List<BoardQnaListDTO> selectQuestionList();
}
