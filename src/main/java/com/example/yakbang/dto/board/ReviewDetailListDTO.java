package com.example.yakbang.dto.board;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewDetailListDTO {
    private Long reviewId;
    private Long pillId;
    private Long memberId;
    private int point;
    private LocalDate modifiedDate;
    private String reviewTitle;
    private String reviewContent;
    private String reviewGender;
    private String reviewShape;
    private int reviewAge;
}
