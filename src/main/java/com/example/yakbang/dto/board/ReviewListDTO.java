package com.example.yakbang.dto.board;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewListDTO {
    private Long reviewId;
    private Long pillId;
    private Long memberId;
    private String name;
    private int point;
    private LocalDate modifiedDate;
    private String reviewTitle;
    private String reviewContent;
    private String reviewGender;
    private String reviewShape;
    private int reviewAge;
    private String companyName;
    private String pillName;

    public String getName() {
        String lastName = name.charAt(0) + "";
        int len = this.name.length();

        for (int i = 0; i < len - 1; i++) {
            lastName += "*";
        }

        return lastName;
    }
}









