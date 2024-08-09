package com.example.yakbang.mapper.pill;

import com.example.yakbang.dto.pill.PillDTO;
import com.example.yakbang.dto.pill.PillItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PillMapper {
    void insertPill(PillItemDTO pillItemDTO);


}













