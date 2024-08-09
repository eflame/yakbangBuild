package com.example.yakbang.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface AdminMapper {
    // 로그인
    Optional<Long> selectAdminMemberId(@Param("loginId") String loginId,
                                       @Param("password") String password);

}













