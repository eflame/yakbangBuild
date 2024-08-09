package com.example.yakbang.mapper.duplicate;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DuplicateMapper {
    int duplicatePill(String itemSeq);
}
