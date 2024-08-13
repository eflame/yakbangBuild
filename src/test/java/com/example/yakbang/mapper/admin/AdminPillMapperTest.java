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
    void setUp() {
        adminPillDTO = AdminPillDTO.builder()
                .itemSeq(1L)
                .categoryId(1)
                .companyName("테스트회사")
                .pillName("약이름")
                .detailContent("이약 효능")
                .pillHowto("섭취방법")
                .intakePrecautions(null)
                .atpnQesitm("섭취주의사항")
                .intrcQesitm(null)
                .seQesitm("부작용")
                .pillDeposit("보관방법")
                .openDate("공개일자")
                .updateDate("수정일자")
                .pillColor("색상")
                .pillShape(null)
                .pillImage("약이미지경로")
                .build();
    }

    @Test
    void selectPillInfo() {
        // Given
        Long itemSeq = adminPillDTO.getItemSeq();
        System.out.println("itemSeq = " + itemSeq);

        // When
        List<AdminPillDTO> pillInfo = adminPillMapper.selectPillInfo(itemSeq);

        // Then
        // itemSeq로 조회한 결과가 null이 아닌지 확인
        assertThat(pillInfo).isNotNull();
        assertThat(pillInfo).isNotEmpty(); // 조회 결과가 비어 있지 않은지 확인

        // 조회된 항목의 itemSeq가 주어진 itemSeq와 일치하는지 확인
        AdminPillDTO retrievedPill = pillInfo.get(0); // 첫 번째 결과를 가져옴 (여러 개일 경우 루프나 추가 검증 필요)
        assertThat(retrievedPill.getItemSeq()).isEqualTo(itemSeq);


    }


}