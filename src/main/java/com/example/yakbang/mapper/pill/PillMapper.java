package com.example.yakbang.mapper.pill;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PillMapper {
    String selectTime();

    @Select("SELECT SYSDATE FROM dual")
    String selectTime2();
}













