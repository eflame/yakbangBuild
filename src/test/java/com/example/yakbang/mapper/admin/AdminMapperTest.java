package com.example.yakbang.mapper.admin;

import com.example.yakbang.dto.admin.AdminDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AdminMapperTest {
    @Autowired AdminMapper adminMapper;
    AdminDTO adminDTO;

    @Test
    void selectAdminMemberId() {
        // Given
        adminDTO = AdminDTO.builder()
                .memberId(0L)
                .loginId("admin")
                .password("1234")
                .build();
        // When
        Long adminId = adminMapper.selectAdminMemberId(adminDTO.getLoginId(), adminDTO.getPassword()).get();
        // Then
        assertThat(adminId).isEqualTo(adminDTO.getMemberId());
    }
}