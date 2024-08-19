package com.example.yakbang.mapper.admin;

import com.example.yakbang.dto.admin.AdminPillDTO;
import com.example.yakbang.dto.page.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminPillMapper {

    List<AdminPillDTO> selectPillInfo(Long itemSeq);

    List<AdminPillDTO> selectPillPageList(PageRequestDTO pageRequestDTO);

    int pillTotal(PageRequestDTO pageRequestDTO);

    void updatePill(AdminPillDTO adminPillDTO);

    int deletePill(Long itemSeq);

}













