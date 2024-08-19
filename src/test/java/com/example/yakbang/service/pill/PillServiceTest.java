package com.example.yakbang.service.pill;

import com.example.yakbang.dto.pill.PillListDTO;
import com.example.yakbang.mapper.pill.PillMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PillServiceTest {
    @Mock
    PillMapper pillMapper;
    @InjectMocks
    PillService pillService;

    @Test
    void findPillData() {
// Given
        String pillName = "활명수";
        List<PillListDTO> mockList = new ArrayList<>();
        mockList.add(new PillListDTO());
//        when(pillMapper.selectPillByName(pillName)).thenReturn(mockList);
        doReturn(mockList).when(pillMapper).selectPillByName(any());

        // When
        List<PillListDTO> result = pillService.findPillData(pillName);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void findPillItemList() {
    }
}