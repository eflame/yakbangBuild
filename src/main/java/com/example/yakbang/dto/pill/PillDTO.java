package com.example.yakbang.dto.pill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class PillDTO {
    private String itemSeq;
    private String entpName; // 제조사
    @JsonProperty("ETC_OTC_NAME")
    private String etcOtcName; // 일반의약품
    private String updateDe; // 업데이트 날짜
    private String itemName; // 제품명
    @JsonProperty("SHAPE")
    private String shape; // 형태
    @JsonProperty("COLOR_CLASS1")
    private String colorClass1; // 색상
    private String useMethodQesitm; // 섭취방법
    private String efcyQesitm; // 효능
    
    private String atpnWarnQesitm; // 섭취시주의사항
    private String atpnQesitm;
    private String intrcQesitm;
    private String seQesitm;

    private String depositMethodQesitm; // 보관방법
    private String itemImage; // 약 이미지

}
