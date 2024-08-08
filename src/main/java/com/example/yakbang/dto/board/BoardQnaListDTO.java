package com.example.yakbang.dto.board;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class BoardQnaListDTO {
   private Long questionID;   // 질문 번호
   private Long memberID;     // 회원 번호
   private Long pillId;       // 약 번호
   private String title;      // 제목
   private String content;    // 내용
   private String answerTitle; // 전문가 답변 제목
   private Long answerNo;     // 답변 번호
}
