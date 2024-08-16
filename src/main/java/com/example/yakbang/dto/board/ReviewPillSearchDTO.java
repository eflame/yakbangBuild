package com.example.yakbang.dto.board;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewPillSearchDTO {
    private Long pillId;
    private String pillName;
}
