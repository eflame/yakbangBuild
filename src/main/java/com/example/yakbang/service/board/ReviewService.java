package com.example.yakbang.service.board;

import com.example.yakbang.dto.board.ReviewDetailListDTO;
import com.example.yakbang.dto.board.ReviewListDTO;
import com.example.yakbang.dto.board.ReviewPillSearchDTO;
import com.example.yakbang.dto.board.ReviewWriteDTO;
import com.example.yakbang.mapper.board.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;

    public void addReview(ReviewWriteDTO reviewWriteDTO){
        reviewMapper.insertReview(reviewWriteDTO);
    }

    public List<ReviewListDTO> findList(String keyword){
        return reviewMapper.selectList(keyword);
    }
    public List<ReviewDetailListDTO> findDetailList(Long pillId){
        return reviewMapper.selectDetailList(pillId);
    }

    public List<ReviewPillSearchDTO> findPillListByName(String pillName){
        return reviewMapper.selectPillListByName(pillName);
    }
}










