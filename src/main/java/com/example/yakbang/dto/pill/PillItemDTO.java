package com.example.yakbang.dto.pill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class PillItemDTO {
    // e-약은요
    private Long pillId;
    @JsonProperty("entpName")
    private String companyName; // 제조사
    @JsonProperty("itemName")
    private String pillName; // 제품명
    private String itemSeq; // 상품시퀀스
    @JsonProperty("efcyQesitm")
    private String detailContent; // 효능
    @JsonProperty("useMethodQesitm")
    private String pillHowto; // 섭취방법
    @JsonProperty("atpnWarnQesitm")
    private String intakePrecautions;// 섭취시주의사항
    @JsonProperty("atpnQesitm")
    private String atpnQesitm; // 주의사항
    @JsonProperty("intrcQesitm")
    private String intrcQesitm; // 상호작용
    @JsonProperty("seQesitm")
    private String seQesitm;  // 부작용
    @JsonProperty("depositMethodQesitm")
    private String pillDeposit; // 보관방법
    @JsonProperty("openDe")
    private String openDate; // 공개 일자
    @JsonProperty("updateDe")
    private String updateDate; // 수정 날짜
    @JsonProperty("itemImage")
    private String pillImage;

    public String getOpenDate(){
        String[] strArr = this.openDate.split(" ");

        return strArr[0];
    }
}
