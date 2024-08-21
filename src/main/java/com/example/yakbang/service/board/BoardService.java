package com.example.yakbang.service.board;

import com.example.yakbang.dto.board.*;
import com.example.yakbang.dto.pill.PageRequest;
import com.example.yakbang.mapper.board.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public void addBoard(BoardQnaWriteDTO boardQnaWriteDTO){

        boardMapper.insertBoardQuestion(boardQnaWriteDTO);
    }

    public List<BoardQnaListDTO> findList() {
        return boardMapper.selectQuestionList();
    }

    public BoardQnaDetailDTO findDetail(Long questionId){
        boardMapper.updateViewCount(questionId); // 조회수 증가
        return boardMapper.selectQuestionDetail(questionId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시물 정보"));
    }

    public List<BoardQnaListDTO> findSearchList(BoardSearchDTO boardSearchDTO){
        return boardMapper.selectBoardSearch(boardSearchDTO);
    }


    // 답변
    public void addAnswer(AnswerWriteDTO answerWriteDTO){
        boardMapper.insertAnswer(answerWriteDTO);
    }

    public List<BoardQnaListDTO> findListWithPage(String keyword, PageRequest pageRequest){
        pageRequest.setAmount(10);
        return boardMapper.selectListWithPage(keyword, pageRequest);
    }

    public void modifyAnswer(AnswerModifyDTO answerModifyDTO) {

        boardMapper.updateAnswer(answerModifyDTO);
    }
}
