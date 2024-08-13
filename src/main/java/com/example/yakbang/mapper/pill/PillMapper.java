package com.example.yakbang.mapper.pill;

import com.example.yakbang.dto.pill.PillDTO;
import com.example.yakbang.dto.pill.PillItemDTO;
import com.example.yakbang.dto.pill.PillOtcDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PillMapper {
    void insertPill(PillItemDTO pillItemDTO);

    void updatePill(PillOtcDTO pillOtcDTO);

    List<PillDTO> findAll();

    @Select("SELECT * FROM TBL_PILL WHERE ITEM_SEQ = #{id}")
    PillDTO findById(@Param("id") Long id);

}













