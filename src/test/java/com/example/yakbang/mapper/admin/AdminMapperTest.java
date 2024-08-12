package com.example.yakbang.mapper.admin;

import com.example.yakbang.dto.admin.AdminDTO;
import com.example.yakbang.dto.admin.AdminMemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AdminMapperTest {
    @Autowired AdminMapper adminMapper;
    AdminDTO adminDTO;
    AdminMemberDTO adminMemberDTO;

    @Test
    void selectAdminMemberId() {
        // Given
        adminDTO = AdminDTO.builder()
                .memberId(0L)
                .loginId("admin")
                .password("1234")
                .build();
        // When
        Long adminId = adminMapper.selectAdminId(adminDTO.getLoginId(), adminDTO.getPassword()).get();
        // Then
        assertThat(adminId).isEqualTo(adminDTO.getMemberId());
    }

    @Test
    void selectAdminMember() {
        // Given
        adminMemberDTO = AdminMemberDTO.builder()
                .memberId(2L)
                .loginId("test123")
                .name("김철수")
                .birth("2000-01-01")
                .gender("M")
                .email("test@test.com")
                .phoneNumber("010-0000-0000")
                .build();
        // When
        List<AdminMemberDTO> list = adminMapper.selectGeneralMembers(adminMemberDTO.getMemberId());

        // Then
        assertThat(list).hasSize(1);
    }
}