package com.example.yakbang.dto.board;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class AnswerWriteDTO {
    private Long answerNo;
    private Long questionId;
    private Long expertId;
    private String answerContent;
}
