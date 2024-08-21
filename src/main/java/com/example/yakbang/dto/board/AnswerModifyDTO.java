package com.example.yakbang.dto.board;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class AnswerModifyDTO {
    private Long answerNo;
    private Long questionId;
    private String answerContent;
}
