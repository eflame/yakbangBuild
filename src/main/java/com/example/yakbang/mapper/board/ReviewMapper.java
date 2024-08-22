package com.example.yakbang.mapper.board;

import com.example.yakbang.dto.board.ReviewDetailListDTO;
import com.example.yakbang.dto.board.ReviewListDTO;
import com.example.yakbang.dto.board.ReviewPillSearchDTO;
import com.example.yakbang.dto.board.ReviewWriteDTO;
import com.example.yakbang.dto.pill.PageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    void insertReview(ReviewWriteDTO reviewWriteDTO);

    List<ReviewListDTO> selectList(String keyword);

    List<ReviewListDTO> selectListWithPage(@Param("keyword") String keyword,
                                           @Param("pageRequest") PageRequest pageRequest);

    List<ReviewPillSearchDTO> selectPillListByName(String pillName);

    List<ReviewListDTO> selectReviewInPillDetail(@Param("itemSeq") String itemSeq, @Param("pageRequest") PageRequest pageRequest);
}
