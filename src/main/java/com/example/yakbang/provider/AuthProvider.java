package com.example.yakbang.provider;

import com.example.yakbang.resp.KakaoAccessTokenResponse;
import com.example.yakbang.resp.KakaoMemberInfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class AuthProvider {
    @Value("${auth.kakao.client-id}")
    private String kakaoClientId;
    @Value("${auth.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String GRANT_TYPE = "authorization_code";

    public String getLocation() {
        WebClient wc = WebClient.builder()
                .baseUrl("https://kauth.kakao.com")
                .build();

        ClientResponse response = wc.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth/authorize")
                        .queryParam("response_type", "code")
                        .queryParam("client_id", this.kakaoClientId)
                        .queryParam("redirect_uri", this.kakaoRedirectUri)
                        .build())
                .exchangeToMono(clientResponse ->
                        Mono.just(clientResponse))
                .block();

        System.out.println("response = " + response);
        System.out.println("response.statusCode() = " + response.statusCode());
        
        URI location = response.headers().asHttpHeaders().getLocation();

        System.out.println("location = " + location);
        
        return location.toString();
    }

    public KakaoAccessTokenResponse getAccessTokenResponse(String code) {
        WebClient wc = WebClient.builder()
                .baseUrl("https://kauth.kakao.com")
                .build();

        KakaoAccessTokenResponse tokenResponse = wc.post()
                .uri(uriBuilder -> uriBuilder
                        .path("oauth/token")
                        .build())
                .headers(httpHeaders ->
                        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED))
                .body(BodyInserters.fromFormData("grant_type", GRANT_TYPE)
                        .with("client_id", this.kakaoClientId)
                        .with("redirect_uri", this.kakaoRedirectUri)
                        .with("code", code)
                ).retrieve()
                .bodyToMono(KakaoAccessTokenResponse.class)
                .block();

        System.out.println("tokenResponse = " + tokenResponse);

        return tokenResponse;
    }

    public KakaoMemberInfoResponse getInfo(String accessToken) {
        WebClient wc = WebClient.builder()
                .baseUrl("https://kapi.kakao.com")
                .build();

        KakaoMemberInfoResponse infoResponse = wc.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/user/me")
                        .build())
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    httpHeaders.set(AUTHORIZATION_HEADER, BEARER_PREFIX + accessToken);
                }).retrieve()
                .bodyToMono(KakaoMemberInfoResponse.class)
                .block();

        System.out.println("infoResponse = " + infoResponse);
        
        return infoResponse;
    }

}
