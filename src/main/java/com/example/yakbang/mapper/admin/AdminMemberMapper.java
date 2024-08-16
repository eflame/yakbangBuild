package com.example.yakbang.mapper.admin;

import com.example.yakbang.dto.admin.AdminExMemberDTO;
import com.example.yakbang.dto.admin.AdminMemberDTO;
import com.example.yakbang.dto.page.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminMemberMapper {
    // 로그인
    Optional<Long> selectAdminId(@Param("loginId") String loginId,
                                       @Param("password") String password);

    // 회원관리
    List<AdminMemberDTO> memberPageList(PageRequestDTO pageRequestDTO);
    List<AdminExMemberDTO> memberExPageList(PageRequestDTO pageRequestDTO);

    List<AdminMemberDTO> selectGeneralMembers(Long memberId);
    List<AdminExMemberDTO> selectExpertMembers(Long expertId);

    // 회원정보수정
    void updateGeneralMember(AdminMemberDTO adminMemberDTO);

    void updateExpertMember(AdminExMemberDTO adminExMemberDTO);

    // 회원탈퇴

    int deleteGeneralMember(Long memberId);

    int deleteExpertMember(Long expertId);

    // 페이지 총 갯수
    int generalMemberTotal(PageRequestDTO pageRequestDTO);

    int expertMemberTotal(PageRequestDTO pageRequestDTO);

}













