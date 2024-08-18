package com.example.yakbang.dto.board;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewWriteDTO {
    private Long reviewId;
    private Long pillId;
    private Long memberId;
    private int point;
    private String reviewTitle;
    private String reviewContent;
    private String reviewGender;
    private String reviewShape;
    private int reviewAge;
}
