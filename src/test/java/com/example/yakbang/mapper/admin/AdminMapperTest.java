package com.example.yakbang.mapper.admin;

import com.example.yakbang.dto.admin.AdminDTO;
import com.example.yakbang.dto.admin.AdminExMemberDTO;
import com.example.yakbang.dto.admin.AdminMemberDTO;
import org.junit.jupiter.api.BeforeEach;
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
    AdminExMemberDTO adminExMemberDTO;


    @BeforeEach
    void setUp() {
        adminMemberDTO = AdminMemberDTO.builder()
                .memberId(1L)
                .loginId("test123")
                .name("김철수")
                .birth("2000-01-01")
                .gender("M")
                .email("test@test.com")
                .phoneNumber("010-0000-0000")
                .build();

        adminExMemberDTO = AdminExMemberDTO.builder()
                .expertId(1L)
                .loginId("id1234")
                .name("김영희")
                .gender("F")
                .birth("2000-01-01")
                .phoneNumber("010-0000-0000")
                .email("test@test.com")
                .job("약사")
                .pharmacyAddress("서울")
                .build();
    }



    @Test
    void testSelectAdminId() {
    }

    @Test
    void testSelectGeneralMembers() {
    }

    @Test
    void testSelectExpertMembers() {
    }

    @Test
    void testUpdateGeneralMember() {
    }

    @Test
    void testUpdateExpertMember() {
    }

    @Test
    void testDeleteGeneralMember() {
    }

    @Test
    void testDeleteExpertIdMember() {
    }
}