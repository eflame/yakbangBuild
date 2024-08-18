package com.example.yakbang.mapper.board;

import com.example.yakbang.dto.board.ReviewDetailListDTO;
import com.example.yakbang.dto.board.ReviewListDTO;
import com.example.yakbang.dto.board.ReviewWriteDTO;
import com.example.yakbang.dto.member.MemberJoinDTO;
import com.example.yakbang.dto.pill.PillItemDTO;
import com.example.yakbang.mapper.member.MemberMapper;
import com.example.yakbang.mapper.pill.PillMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewMapperTest {
    @Autowired ReviewMapper reviewMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    PillMapper pillMapper;

    MemberJoinDTO memberJoinDTO;
    ReviewWriteDTO reviewWriteDTO;
    PillItemDTO pillItemDTO;



    @BeforeEach
    void setUp() {


        memberJoinDTO = MemberJoinDTO.builder()
                .birth("2000-01-01")
                .loginId("my-test")
                .password("1234")
                .email("test@naver.com")
                .gender("M")
                .name("test")
                .phoneNumber("010-1234-1234")
                .build();

        memberMapper.insertMember(memberJoinDTO);

//        pillItemDTO = PillItemDTO.builder()
//                .atpnQesitm("test")
//                .companyName("test")
//                .detailContent("test")
//                .intakePrecautions("test")
//                .intrcQesitm("test")
//                .itemSeq("99")
//                .build();
//
//        pillMapper.insertPill(pillItemDTO);

        reviewWriteDTO = ReviewWriteDTO.builder()
                .pillId(20905L)
                .memberId(memberJoinDTO.getMemberId())
                .point(3)
//                .reviewPhoto("test")
                .reviewContent("test")
                .build();


    }

    @Test
    void insertReview() {


        reviewMapper.insertReview(reviewWriteDTO);
    }

    @Test
    void selectList() {
        reviewMapper.insertReview(reviewWriteDTO);

        List<ReviewListDTO> list = reviewMapper.selectList("");

        assertThat(list)
                .extracting(ReviewListDTO::getReviewContent)
                .contains("test");
    }

    @Test
    void selectDetailList() {
        reviewMapper.insertReview(reviewWriteDTO);

        List<ReviewDetailListDTO> list = reviewMapper.selectDetailList(reviewWriteDTO.getPillId());

        assertThat(list)
                .extracting(ReviewDetailListDTO::getReviewContent)
                .contains("test");
    }
}