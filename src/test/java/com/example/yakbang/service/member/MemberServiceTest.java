package com.example.yakbang.service.member;

import com.example.yakbang.dto.member.MemberJoinDTO;
import com.example.yakbang.dto.member.MemberMypageDTO;
import com.example.yakbang.mapper.member.MemberMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberMapper memberMapper;

    @InjectMocks
    MemberService memberService;

    @Test
    void addMember() {
        // given
        doReturn(0).when(memberMapper).selectLoginIdCount(any());
        doNothing().when(memberMapper).insertMember(any());
        // when
        memberService.addMember(MemberJoinDTO.builder().build());
        // then
        verify(memberMapper, times(1)).selectLoginIdCount(any());
        verify(memberMapper, times(1)).insertMember(any());
    }

    @Test
    void findMemberId() {
    }

    @Test
    void isDuplicateLoginId() {
    }

    @Test
    void findMemberInfo() {
    }

    @Test
    void modifyMemberInfo() {

    }

    @Test
    void removeMemberInfo() {
    }

    @Test
    void findLoginId() {
//        given
        doReturn(Optional.of("")).when(memberMapper).selectLoginId(any(),any());
//        when
        String loginId = memberService.findLoginId(any(), any());
//        then
        assertNotNull(loginId);
    }

    @Test
    void findPassword() {
        doReturn(Optional.of("")).when(memberMapper).selectPassword(any(),any());
        String password = memberService.findPassword(any(), any());
        assertThat(password).isNotNull();
    }
}