package com.example.yakbang.mapper.admin;

import com.example.yakbang.dto.admin.AdminQnaDTO;
import com.example.yakbang.dto.admin.AdminReviewDTO;
import com.example.yakbang.dto.page.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminBoardMapper {

    List<AdminQnaDTO> selectQnaList(PageRequestDTO pageRequestDTO);

    int qnaTotal(PageRequestDTO pageRequestDTO);

    List<AdminQnaDTO> selectQnaInfo(Long questionId);

    int deleteQna(Long questionId);


    List<AdminReviewDTO> selectReviewList(PageRequestDTO pageRequestDTO);

    int reviewTotal(PageRequestDTO pageRequestDTO);

    List<AdminReviewDTO> selectReviewInfo(Long reviewId);

    int deleteReview(Long reviewId);

}
