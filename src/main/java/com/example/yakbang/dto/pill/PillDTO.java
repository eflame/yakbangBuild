package com.example.yakbang.dto.pill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PillDTO {
    @JsonProperty("companyName")
    private String companyName; // 제조사 (COMPANY_NAME)

    @JsonProperty("pillName")
    private String pillName; // 제품명 (PILL_NAME)

    private Long itemSeq; // 상품 시퀀스 (ITEM_SEQ, TYPE NUMBER)

    @JsonProperty("detailContent")
    private String detailContent; // 효능 (DETAIL_CONTENT)

    @JsonProperty("pillHowto")
    private String pillHowto; // 섭취 방법 (PILL_HOWTO)

    @JsonProperty("intakePrecautions")
    private String intakePrecautions; // 섭취 시 주의 사항 (INTAKE_PRECAUTIONS, CLOB)

    @JsonProperty("atpnQesitm")
    private String atpnQesitm; // 주의 사항 (ATPNQESITM, CLOB)

    @JsonProperty("intrcQesitm")
    private String intrcQesitm; // 상호작용 (INTRCQESITM, CLOB)

    @JsonProperty("seQesitm")
    private String seQesitm; // 부작용 (SEQESITM, CLOB)

    @JsonProperty("pillDeposit")
    private String pillDeposit; // 보관 방법 (PILL_DEPOSIT)

    @JsonProperty("openDate")
    private String openDate; // 공개 일자 (OPEN_DATE, DATE)
    // 변환된 날짜를 반환하는 메소드
    public String getFormattedOpenDate() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // API 제공 형식
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 형식 변경
        LocalDate date = LocalDate.parse(openDate, inputFormatter);
        return date.format(outputFormatter);
    }

    @JsonProperty("updateDate")
    private String updateDate; // 수정 날짜 (UPDATE_DATE, DATE)

    @JsonProperty("pillColor")
    private String pillColor; // 색상 (PILL_COLOR)

    @JsonProperty("pillShape")
    private String pillShape; // 형태 (PILL_SHAPE)

    @JsonProperty("pillImage")
    private String pillImage; // 약 이미지 (PILL_IMAGE, CLOB)
}
