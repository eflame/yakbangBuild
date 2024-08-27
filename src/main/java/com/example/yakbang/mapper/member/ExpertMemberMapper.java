package com.example.yakbang.mapper.member;

import com.example.yakbang.dto.member.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ExpertMemberMapper {
//    회원가입
    void insertExpertMember(MemberJoinDTO memberJoinDTO);

    //  카카오아이디찾기
    Optional<KakaoInfoDTO> selectExKakaoId(@Param("kakaoId") Long kakaoId );

    //    로그인
    Optional<Long> selectExpertMemberId(@Param("loginId") String loginId,
                                                                        @Param("password") String password);
    // 아이디 중복 검사
    int selectExpertLoginIdCount(String loginId);

    //    회원의 정보 마이페이지에서 조회
    Optional<ExpertMypageDTO> selectExpertInfo(Long expertId);

    //    마이페이지 정보수정
    void updateExpertInfo(MemberModifyDTO memberModifyDTO);

    //    회원 정보 삭제
    void deleteExpertInfo(Long expertId);

    //    ID 찾기
    Optional<String> selectExpertLoginId(String name, String email);

    //    PASSWORD 찾기
    Optional<String> selectExpertPassword(String loginId, String email);
}
