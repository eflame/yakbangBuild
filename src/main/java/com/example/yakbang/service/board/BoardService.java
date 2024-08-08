package com.example.yakbang.service.board;

import com.example.yakbang.dto.board.BoardQnaListDTO;
import com.example.yakbang.mapper.board.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public List<BoardQnaListDTO> findList() {
        return boardMapper.selectQuestionList();
    }
}
