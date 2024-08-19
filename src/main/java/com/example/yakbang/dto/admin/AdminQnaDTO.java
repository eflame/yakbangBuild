package com.example.yakbang.dto.admin;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class AdminQnaDTO {
    private Long questionId;   // 질문 번호
    private Long memberId;     // 회원 번호
    private String loginId;    // 회원 아이디
    private String title;      // 제목
    private String content;    // 내용
    private Long viewCount;    // 조회수
    private String name;       // 회원 이름
    private String age;   // 회원 나이
    private String gender;     // 회원 성별
    private LocalDate writeDate;  // 작성날짜
    private Long answerNo;     // 답변 번호
    private String answerContent; // 전문가 답변 제목

}
