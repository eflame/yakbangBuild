package com.example.yakbang.service.admin;

import com.example.yakbang.dto.admin.AdminQnaDTO;
import com.example.yakbang.dto.page.PageRequestDTO;
import com.example.yakbang.mapper.admin.AdminBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminBoardService {

    private final AdminBoardMapper adminBoardMapper;

    public List<AdminQnaDTO> findQnaInfo(Long questionId) {
        return adminBoardMapper.selectQnaInfo(questionId);
    }

    public List<AdminQnaDTO> findQnaList(PageRequestDTO pageRequestDTO){
        return adminBoardMapper.selectQnaList(pageRequestDTO);
    }

    public int qnaTotal(PageRequestDTO pageRequestDTO){
        return adminBoardMapper.qnaTotal(pageRequestDTO);
    }

}
