package com.example.yakbang.dto.board;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class BoardReviewDTO {
    private Long reviewId;              // 리뷰 번호
    private Long pillId;                // 약 번호
    private Long categoryId;            // 카테고리 번호
    private Long memberId;              // 회원 번호
    private Long point;                 // 포인트
    private Character reviewPhoto;      // 리뷰 사진
    private LocalDate createDate;       // 생성 날짜
    private LocalDate modifiedDate;     // 수정된 날짜
    private Long viewCount;             // 조회수
    private Character reviewComment;    // 리뷰 답변

    private Long commentId;             // 답변 번호
    private Long expertId;              // 전문가 번호
    private Character content;          // 리뷰 내용
    private LocalDate commentDate;      // 답변 날짜




}
