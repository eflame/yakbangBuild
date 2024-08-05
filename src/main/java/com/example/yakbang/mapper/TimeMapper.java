package com.example.yakbang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {
    String selectTime();

    @Select("SELECT SYSDATE FROM dual")
    String selectTime2();
}













