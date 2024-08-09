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


        // when


        // then

    }

    @Test
    void genericTest(){


    }
}