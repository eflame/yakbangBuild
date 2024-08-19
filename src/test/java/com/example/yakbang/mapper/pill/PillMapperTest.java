package com.example.yakbang.mapper.pill;


import com.example.yakbang.dto.pill.PillItemDTO;
import com.example.yakbang.dto.pill.PillListDTO;
import com.example.yakbang.dto.pill.PillOtcDTO;
import com.example.yakbang.mapper.member.MemberMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
class PillMapperTest {
    private static final Logger log = LoggerFactory.getLogger(PillMapperTest.class);
    @Autowired PillMapper pillMapper;
    PillItemDTO pillItemDTO;
    PillOtcDTO pillOtcDTO;

    @Autowired
    private MemberMapper memberMapper;

    @Test
    void updatePill() {
        // given
        pillOtcDTO = PillOtcDTO.builder()
                .itemSeq(String.valueOf(1))
                .pillShape("모양넣기")
                .pillColor("컬러넣기")
                .pillImage("asdf")
                .build();
        // when
        pillMapper.updatePill(pillOtcDTO);
        // then
        String pillImage = pillOtcDTO.getPillImage();

    }



    @Test
    void selectPillByName(){
//        given
        PillListDTO pillName = PillListDTO.builder()
                .pillName("test").build();
//        when
        List<PillListDTO> list = pillMapper.selectPillByName("활명수");
//        then
        System.out.println("list = " + list);

    }
}