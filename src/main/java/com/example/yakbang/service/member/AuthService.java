package com.example.yakbang.service.member;

import com.example.yakbang.dto.member.KakaoInfoDTO;
import com.example.yakbang.mapper.member.ExpertMemberMapper;
import com.example.yakbang.mapper.member.MemberMapper;
import com.example.yakbang.provider.AuthProvider;
import com.example.yakbang.resp.KakaoMemberInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberMapper memberMapper;
    private final ExpertMemberMapper expertMemberMapper;
    private final AuthProvider authProvider;

    public String getKakaoLoginURI() {
        return authProvider.getLocation();
    }

    public KakaoInfoDTO getMemberIdByKakaoInfo(String code) {
        String accessToken = authProvider.getAccessTokenResponse(code)
                .getAccessToken();

        KakaoMemberInfoResponse infoResponse = authProvider.getInfo(accessToken);
        Long kakaoId = infoResponse.getId();
        System.out.println("kakaoId = " + kakaoId);

        String nickname = infoResponse.getProperties().getNickname();
        System.out.println("nickname = " + nickname);
        Optional<KakaoInfoDTO> optionalKakaoInfo = memberMapper.selectKakaoId(kakaoId);
        System.out.println("optionalKakaoInfo = " + optionalKakaoInfo);

        if (optionalKakaoInfo.isPresent()) {
            KakaoInfoDTO kakaoInfoDTO = optionalKakaoInfo.get();
            kakaoInfoDTO.setMemberType("general");
            System.out.println("kakaoInfoDTO.getMemberType() = " + kakaoInfoDTO.getMemberType());
            return kakaoInfoDTO;
        } else {
            // 첫 번째 테이블에 결과가 없으면, 두 번째 테이블에서 조회
            KakaoInfoDTO expertKakaoInfoDTO = expertMemberMapper.selectExKakaoId(kakaoId)
                    .orElseGet(() -> new KakaoInfoDTO(kakaoId, nickname, null, null));

            if (expertKakaoInfoDTO.getMemberId() != null) {
                expertKakaoInfoDTO.setMemberType("expert");
                return expertKakaoInfoDTO;
            } else {
                KakaoInfoDTO kakaoJoin = expertKakaoInfoDTO;
                return kakaoJoin;
            }
        }
    }
}
