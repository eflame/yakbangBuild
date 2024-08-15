package com.example.yakbang.service.admin;

import com.example.yakbang.dto.admin.AdminExMemberDTO;
import com.example.yakbang.dto.admin.AdminMemberDTO;
import com.example.yakbang.dto.page.PageRequestDTO;
import com.example.yakbang.mapper.admin.AdminMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminMemberService {
    private final AdminMemberMapper adminMemberMapper;
    
    // 관리자 로그인
    public Long findAdminId(String loginId, String password) {
        return adminMemberMapper.selectAdminId(loginId, password)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 관리자 정보"));
    }
    
    // 일반 회원 조회
    public List<AdminMemberDTO> findMemberPageNation(PageRequestDTO pageRequestDTO) {
        return adminMemberMapper.memberPageList(pageRequestDTO);
    }

    public List<AdminMemberDTO> findGeneralMembers(Long memberId) {
        return adminMemberMapper.selectGeneralMembers(memberId);
    }

    // 전문가 회원 조회
    public List<AdminExMemberDTO> findMemberExPageNation(PageRequestDTO pageRequestDTO) {
        return adminMemberMapper.memberExPageList(pageRequestDTO);
    }
    public List<AdminExMemberDTO> findExpertMembers(Long expertId) {
        return adminMemberMapper.selectExpertMembers(expertId);
    }



    // 일반 회원 업데이트
    public void modifyGeneralMember(AdminMemberDTO adminMemberDTO) {
        adminMemberMapper.updateGeneralMember(adminMemberDTO);
    }

    // 전문가 회원 업데이트
    public void modifyExpertMember(AdminExMemberDTO adminExMemberDTO) {
        adminMemberMapper.updateExpertMember(adminExMemberDTO);
    }

    // 일반 회원 삭제
    public int deleteGeneral(Long memberId) {
        return adminMemberMapper.deleteGeneralMember(memberId);
    }
    
    // 전문가 회원 삭제
    public int deleteExpert(Long expertId) {
        return adminMemberMapper.deleteExpertMember(expertId);
    }

    //페이지
    public int findGeneralTotal(PageRequestDTO pageRequestDTO){
        return adminMemberMapper.generalMemberTotal(pageRequestDTO);
    }

    public int findExpertTotal(PageRequestDTO pageRequestDTO){
        return adminMemberMapper.expertMemberTotal(pageRequestDTO);
    }
}
