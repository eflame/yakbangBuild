package com.example.yakbang.dto.admin;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class AdminPillDTO {
    private Long itemSeq; // 상품시퀀스
    private String companyName; // 제조사
    private String pillName; // 제품명
    private String detailContent; // 효능
    private String pillHowto; // 섭취방법
    private String intakePrecautions; // 섭취시주의사항
    private String atpnQesitm; // 주의사항
    private String intrcQesitm; // 상호작용
    private String seQesitm; // 부작용
    private String pillDeposit; // 보관방법
    private String openDate; // 공개 일자
    private String updateDate; // 수정 일자
    private String pillColor; // 색상
    private String pillShape; // 형태
    private String pillImage; // 약 이미지
}
