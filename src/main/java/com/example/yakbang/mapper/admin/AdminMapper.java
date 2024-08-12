package com.example.yakbang.mapper.admin;

import com.example.yakbang.dto.admin.AdminExMemberDTO;
import com.example.yakbang.dto.admin.AdminMemberDTO;
import com.example.yakbang.dto.member.MemberMypageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminMapper {
    // 로그인
    Optional<Long> selectAdminId(@Param("loginId") String loginId,
                                       @Param("password") String password);

    // 회원관리
    List<AdminMemberDTO> selectGeneralMembers(Long memberId);
    List<AdminExMemberDTO> selectExpertMembers(Long expertId);

    // 회원정보 수정
    void updateGeneralMember(AdminMemberDTO adminMemberDTO);

    void updateExpertMember(AdminExMemberDTO adminExMemberDTO);

    List<AdminMemberDTO> selectAdminList();
}













