package com.example.yakbang.mapper.pill;

import com.example.yakbang.dto.pill.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PillMapper {
    void insertPill(PillItemDTO pillItemDTO);

    void updatePill(PillOtcDTO pillOtcDTO);

    @Select("SELECT * FROM TBL_PILL WHERE ITEM_SEQ = #{id}")
    PillDTO findById(@Param("id") Long id);

    List<PillListDTO> selectListWithPage(PageRequest pageRequest);

    //    제품명 검색시 데이터 조회
    List<PillListDTO> selectPillByName(String pillName);

    //    증상 검색시 데이터 조회
    List<PillListDTO> selectPillByContent(String detailContent);

    //    별점 조회
    void selectPillPoint(Long pillId);
}













