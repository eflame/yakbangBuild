package com.example.yakbang.mapper.board;

import com.example.yakbang.dto.board.ReviewDetailListDTO;
import com.example.yakbang.dto.board.ReviewListDTO;
import com.example.yakbang.dto.board.ReviewPillSearchDTO;
import com.example.yakbang.dto.board.ReviewWriteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    void insertReview(ReviewWriteDTO reviewWriteDTO);

    List<ReviewListDTO> selectList(String keyword);

    List<ReviewDetailListDTO> selectDetailList(Long pillId);

    List<ReviewPillSearchDTO> selectPillListByName(String pillName);
}
