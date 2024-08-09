package com.example.yakbang.service.board;

import com.example.yakbang.dto.board.BoardQnaDetailDTO;
import com.example.yakbang.dto.board.BoardQnaListDTO;
import com.example.yakbang.dto.board.BoardQnaWriteDTO;
import com.example.yakbang.mapper.board.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private BoardMapper boardMapper;

    public void addBoard(BoardQnaWriteDTO boardQnaWriteDTO){
        boardMapper.insertBoardQuestion(boardQnaWriteDTO);
    }

    public List<BoardQnaListDTO> findList() {
        return boardMapper.selectQuestionList();
    }

    public List<BoardQnaDetailDTO> findDetail(Long questionId){
        return List.of(boardMapper.selectQuestionDetail(questionId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시물 정보")));
    }
}
