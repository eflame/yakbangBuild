package com.example.yakbang.dto.member;

import lombok.Data;

@Data
public class KakaoInfoDTO {
    private Long kakaoId;
    private String nickName;
    private Long memberId;
    private String memberType;


    // 매개변수가 있는 생성자 추가
    public KakaoInfoDTO(Long kakaoId, String nickName, Long memberId, String memberType) {
        this.kakaoId = kakaoId;
        this.nickName = nickName;
        this.memberId = memberId;
        this.memberType = memberType;
    }
}
