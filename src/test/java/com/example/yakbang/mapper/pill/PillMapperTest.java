package com.example.yakbang.mapper.pill;

import com.example.yakbang.dto.pill.PillApiDTO;
import com.example.yakbang.dto.pill.PillDTO;
import com.example.yakbang.dto.pill.PillItemDTO;
import com.example.yakbang.dto.pill.PillOtcDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PillMapperTest {
    @Autowired PillMapper pillMapper;

    PillDTO pillDTO;

    @Test
    void insertPill() {
        // given
        pillDTO = PillDTO.builder()
//                .itemSeq("201109900")
//                .categoryId(1)
                .pillName("비맥스골드정")
                .updateDate("2021-01-29")
                .detailContent("효능")
                .pillHowto("섭취방법")
//                .sideCaution("섭취시 주의사항1")
                .pillColor("주황")
                .pillShape("타원형")
                .pillDeposit("보관방법")
                .pillImage("이미지 경로")
                .companyName("(유)한풍제약")
                .build();

//        pillMapper.insertPill(pillDTO);

        // when


        // then

    }

    @Test
    void genericTest(){


    }
}