package com.example.yakbang.dto.board;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class BoardQnaDetailDTO {
   private Long questionID;   // 질문번호
   private Long memberID;     // 회원번호
   private Long pillId;       // 약번호
   private String title;      // 제목
   private String content;    // 내용
   private Long viewCount;    // 조회수
   private LocalDate writeDate;  // 작성날짜
   private String answerContent; // 전문가 답변 내용
   private LocalDate answerDate; // 답변 날짜
   private Long answerNo;     // 답변 번호
}
