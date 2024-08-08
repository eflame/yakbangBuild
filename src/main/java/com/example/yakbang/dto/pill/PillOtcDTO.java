package com.example.yakbang.dto.pill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class PillOtcDTO {
    // 의약품 낱알식별 정보
    private String itemSeq; // 상품시퀀스
    @JsonProperty("COLOR_CLASS1")
    private String pillColor; // 색상
    @JsonProperty("SHAPE")
    private String pillShape; // 형태
    @JsonProperty("itemImage")
    private String pillImage ; // 약 이미지
    @JsonProperty("ETC_OTC_NAME")
    private String etcOtcName; // 일반의약품, 전문의약품
}
