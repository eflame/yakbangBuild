package com.example.yakbang.mapper.admin;

import com.example.yakbang.dto.admin.AdminPillDTO;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class AdminPillMapperTest {

    private static final Logger log = LoggerFactory.getLogger(AdminPillMapperTest.class);
    @Autowired private AdminPillMapper adminPillMapper;
    AdminPillDTO adminPillDTO;


    @BeforeEach
    void setUp() {}

    @Test
    void selectPillInfo() {
        // Given
        Long itemSeq = adminPillDTO.getItemSeq();
        System.out.println("itemSeq = " + itemSeq);

        // When
        List<AdminPillDTO> pillInfo = adminPillMapper.selectPillInfo(202107320L);
        System.out.println("pillInfo = " + pillInfo);
        
        // Then
        // itemSeq로 조회한 결과가 null이 아닌지 확인
        assertThat(pillInfo).isNotNull();
        assertThat(pillInfo).isNotEmpty(); // 조회 결과가 비어 있지 않은지 확인

    }

    @Test
    void selectPillPageList() {
        // Given

        // When

        // Then
    }

    @Test
    void pillTotal() {
    }
}