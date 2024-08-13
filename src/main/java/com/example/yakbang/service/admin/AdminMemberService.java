package com.example.yakbang.service.admin;

import com.example.yakbang.dto.admin.AdminExMemberDTO;
import com.example.yakbang.dto.admin.AdminMemberDTO;
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
    public List<AdminMemberDTO> findGeneralMembers(Long memberId) {
        return adminMemberMapper.selectGeneralMembers(memberId);
    }

    // 전문가 회원 조회
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
    public boolean deleteGeneralMember(Long memberId) {
        adminMemberMapper.deleteGeneralMember(memberId);
        return false;
    }
    
    // 전문가 회원 삭제
    public void deleteExpertMember(Long expertId) {
        adminMemberMapper.deleteExpertMember(expertId);
    }

}
