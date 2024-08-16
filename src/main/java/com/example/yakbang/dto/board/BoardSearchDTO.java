package com.example.yakbang.dto.board;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class BoardSearchDTO {
    private String keyword;    // 검색 키워드
}
