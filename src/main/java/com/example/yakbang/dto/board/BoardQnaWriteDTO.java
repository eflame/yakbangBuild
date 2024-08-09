package com.example.yakbang.dto.board;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class BoardQnaWriteDTO {
    private Long questionId;    // 질문 번호
    private String title;       // 제목
    private String content;     // 내용
    private Long memberId;      // 회원 번호
}
