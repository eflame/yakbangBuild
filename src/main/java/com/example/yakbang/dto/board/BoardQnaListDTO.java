package com.example.yakbang.dto.board;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class BoardQnaListDTO {
   private Long questionId;   // 질문 번호
   private Long memberId;     // 회원 번호
   private String title;      // 제목
   private String content;    // 내용
   private String answerTitle; // 전문가 답변 제목
   private Long answerNo;     // 답변 번호
   private String name;       // 회원 이름
   private String gender;     // 회원 성별
   private String age;   // 회원 나이
}
