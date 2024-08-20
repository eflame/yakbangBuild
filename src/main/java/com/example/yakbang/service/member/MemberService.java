package com.example.yakbang.service.member;

import com.example.yakbang.dto.member.MemberJoinDTO;
import com.example.yakbang.dto.member.MemberModifyDTO;
import com.example.yakbang.dto.member.MemberMypageDTO;
import com.example.yakbang.mapper.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    /**
     * 중복 아이디 처리와 회원 가입 처리
     *
     * @param memberJoinDTO 회원 정보
     */
    public void addMember(MemberJoinDTO memberJoinDTO) {

        int cnt = memberMapper.selectLoginIdCount(memberJoinDTO.getLoginId());

        if(cnt != 0) {
            throw new IllegalStateException("중복된 회원 아이디");
        }

        memberMapper.insertMember(memberJoinDTO);
    }
//    로그인 서비스 Optional 반환할 경우 예외발생(예외처리문구)
    public Long findMemberId(String loginId, String password) {
        return memberMapper.selectMemberId(loginId, password)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 정보"));
    }

//    중복될 경우 0이상의 정수를 반환하게 하여 회원가입처리를 막기 위함
    public boolean isDuplicateLoginId(String loginId) {
        return memberMapper.selectLoginIdCount(loginId) > 0;
    }

//    마이페이지 조회하려고하지만 Optional 반환될 경우 예외처리
    public MemberMypageDTO searchMember(Long memberId) {
        return memberMapper.selectMemberInfo(memberId)
                .orElseThrow(()->new IllegalArgumentException("회원의 정보를 확인할 수 없습니다."));
    }



    public void modifyMemberInfo(MemberModifyDTO memberModifyDTO) {
        memberMapper.updateMemberInfo(memberModifyDTO);
    }

    public void removeMemberInfo(Long memberId) {
        memberMapper.deleteMemberInfo(memberId);
    }

    public String findLoginId(String name, String email) {
        return memberMapper.selectLoginId(name, email)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 회원의 ID가 없습니다"));
    }

    public String findPassword(String loginId, String email) {
        return memberMapper.selectPassword(loginId, email)
                .orElseThrow(()->new IllegalArgumentException("일치하는 회원의 비밀번호가 없습니다."));
    }

}


























