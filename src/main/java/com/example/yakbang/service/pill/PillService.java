package com.example.yakbang.service.pill;

import com.example.yakbang.dto.pill.PageRequest;
import com.example.yakbang.dto.pill.PillDTO;
import com.example.yakbang.dto.pill.PillListDTO;
import com.example.yakbang.mapper.pill.PillMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PillService {
    private final PillMapper pillMapper;

    public PillDTO getPillById(Long id) {
        return pillMapper.findById(id);
    }

    public List<PillListDTO> findListWithPage(PageRequest pageRequest) {
        pageRequest.setAmount(30);

        return pillMapper.selectListWithPage(pageRequest);
    }

//    pillName를 통해 해당 제품의 정보 조회
    public List<PillListDTO> findPillData(String pillName){

        List<PillListDTO> list = new ArrayList<>();
        list = pillMapper.selectPillByName(pillName);

        if (list.isEmpty()) {
            throw new IllegalArgumentException("해당 키워드에 관한 데이터를 읽어올 수 없습니다.");
        }
        return list;
    }

//    keyword검색을 통해 제품의 정보 조회
    public List<PillListDTO> findPillItemList(String detailContent){

        List<PillListDTO> list = new ArrayList<>();

        list = pillMapper.selectPillByContent(detailContent);

        if (list.isEmpty()) {
            throw new IllegalArgumentException("해당 키워드에 관한 데이터를 읽어올 수 없습니다.");
        }
        return list;
    }


}




















