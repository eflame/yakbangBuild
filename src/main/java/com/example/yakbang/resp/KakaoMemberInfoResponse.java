package com.example.yakbang.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class KakaoMemberInfoResponse {
    private Long id;
    @JsonProperty("connected_at")
    private String connectedAt;
    private KakaoProperties properties;
}
