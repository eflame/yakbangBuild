package com.example.yakbang.mapper.member;

import com.example.yakbang.dto.member.MemberJoinDTO;
import com.example.yakbang.dto.member.MemberMypageDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberMapperTest {

    @Autowired MemberMapper memberMapper;
    MemberJoinDTO memberJoinDTO;

    @BeforeEach
    void setUp() {
        memberJoinDTO = MemberJoinDTO.builder()
                .loginId("test")
                .password("1234")
                .phoneNumber("010-1234-5678")
                .name("test")
                .birth("2024/08/07")
                .email("test@gmail.com")
                .gender("F").build();


    }
    @Test
    void insertMember() {
        // given
        memberMapper.insertMember(memberJoinDTO);
        // when
        Long memberId = memberMapper.selectMemberId(memberJoinDTO.getLoginId(), memberJoinDTO.getPassword())
                .get();
        // then
        assertThat(memberId).isEqualTo(memberJoinDTO.getMemberId());
    }

    @Test
    void selectLoginIdCount() {
        // given
        memberMapper.insertMember(memberJoinDTO);
        // when
        int cnt = memberMapper.selectLoginIdCount(memberJoinDTO.getLoginId());
        // then
        assertThat(cnt).isEqualTo(1);
    }

    @Test
    void selectMemberInfo() {
//        given
        memberMapper.insertMember(memberJoinDTO);
//        when
        MemberMypageDTO memberMypageDTO = memberMapper.selectMemberInfo(memberJoinDTO.getMemberId()).get();
//        then
        Assertions.assertThat(memberMypageDTO.getName()).isEqualTo(memberJoinDTO.getName());
    }

    @Test
    void updateMemberInfo() {
//        given
        memberMapper.insertMember(memberJoinDTO);
        MemberMypageDTO memberMypageDTO = MemberMypageDTO.builder()
                .phoneNumber("010-1234-5678")
                .email("update test@gmail.com")
                .birth("2024/08/07")
                .memberId(memberJoinDTO.getMemberId()).build();
//        when
        memberMapper.updateMemberInfo(memberMypageDTO);
        Long memberId = memberMapper.selectMemberId(memberJoinDTO.getLoginId(), memberJoinDTO.getPassword()).get();
//        then
        assertThat(memberId).isEqualTo(memberMypageDTO.getMemberId());
    }

    @Test
    void deleteMemberInfo() {
        // given
        memberMapper.insertMember(memberJoinDTO);
        // when
        memberMapper.deleteMemberInfo(memberJoinDTO.getMemberId());
        // then
        Long memberId = memberMapper.selectMemberId(memberJoinDTO.getLoginId(), memberJoinDTO.getPassword())
                .orElse(null);
        assertThat(memberId).isNull();
    }
}

























