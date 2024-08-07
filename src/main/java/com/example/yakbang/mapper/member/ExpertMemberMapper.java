package com.example.yakbang.mapper.member;

import com.example.yakbang.dto.member.ExpertMemberJoinDTO;
import com.example.yakbang.dto.member.MemberJoinDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ExpertMemberMapper {
//    회원가입
    void insertExpertMember(ExpertMemberJoinDTO expertMemberJoinDTO);
//    로그인
    Optional<MemberJoinDTO> selectExpertMemberId(@Param("loginId") String loginId,
                                                                        @Param("password") String password);
    // 아이디 중복 검사
    int selectExpertLoginIdCount(String loginId);
}
