package com.example.yakbang.service.member;

import com.example.yakbang.dto.member.*;
import com.example.yakbang.mapper.member.ExpertMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExpertService {
    private final ExpertMemberMapper expertMemberMapper;

    public void addExpert(MemberJoinDTO memberJoinDTO) {
        int cnt = expertMemberMapper.selectExpertLoginIdCount(memberJoinDTO.getLoginId());

        if(cnt != 0) {
            throw new IllegalStateException("중복된 회원 아이디");
        }
        if(memberJoinDTO.getJob()!=null){
            expertMemberMapper.insertExpertMember(memberJoinDTO);
        }

    }
    //    로그인 서비스 Optional 반환할 경우 예외발생(예외처리문구)
    public Long findExpertId(String loginId, String password) {
        return expertMemberMapper.selectExpertMemberId(loginId, password)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 정보"));
    }

    //    중복될 경우 0이상의 정수를 반환하게 하여 회원가입처리를 막기 위함
    public boolean isDuplicateLoginId(String loginId) {
        return expertMemberMapper.selectExpertLoginIdCount(loginId) > 0;
    }

    //    마이페이지 조회하려고하지만 Optional 반환될 경우 예외처리
    public ExpertMypageDTO searchMember(Long expertId) {
        return expertMemberMapper.selectExpertInfo(expertId)
                .orElseThrow(()->new IllegalArgumentException("회원의 정보를 확인할 수 없습니다."));
    }



    public void modifyExpertInfo(MemberModifyDTO memberModifyDTO) {
        expertMemberMapper.updateExpertInfo(memberModifyDTO);
    }

    public void removeExpertInfo(Long expertId) {
        expertMemberMapper.deleteExpertInfo(expertId);
    }

    public String findExpertLoginId(String name, String email) {
        return expertMemberMapper.selectExpertLoginId(name, email)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 회원의 ID가 없습니다"));
    }

    public String findExpertPassword(String loginId, String email) {
        return expertMemberMapper.selectExpertPassword(loginId, email)
                .orElseThrow(()->new IllegalArgumentException("일치하는 회원의 비밀번호가 없습니다."));
    }

}
