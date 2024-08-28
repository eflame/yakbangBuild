package com.example.yakbang.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class KakaoInfoDTO {
    private Long kakaoId;
    private String nickName;
    private Long memberId;
    private String memberType;
}
