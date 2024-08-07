package com.example.yakbang.dto.pill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class PillItemDTO {
    //    private String itemSeq;
    private int categoryId;
    @JsonProperty("itemName")
    private String pillName; // 제품명
    @JsonProperty("updateDe")
    private String updateDate; // 업데이트 날짜
    @JsonProperty("efcyQesitm")
    private String detailContent; // 효능
    @JsonProperty("useMethodQesitm")
    private String pillHowto; // 섭취방법
    @JsonProperty("atpnWarnQesitm")
    private String sideCaution;// 섭취시주의사항

    private String atpnWarnQesitm;
    private String atpnQesitm;
    private String intrcQesitm;
    private String seQesitm;

    @JsonProperty("COLOR_CLASS1")
    private String pillColor; // 색상
    @JsonProperty("SHAPE")
    private String pillShape; // 형태
    @JsonProperty("depositMethodQesitm")
    private String pillDeposit; // 보관방법
    @JsonProperty("itemImage")
    private String pillImage ; // 약 이미지
    @JsonProperty("entpName")
    private String companyName; // 제조사
    @JsonProperty("ETC_OTC_NAME")
    private String etcOtcName;
}
