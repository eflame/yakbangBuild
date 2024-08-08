package com.example.yakbang.batch;

import com.example.yakbang.dto.pill.PillItemDTO;
import com.example.yakbang.mapper.duplicate.DuplicateMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PillItemProcessor {
    private final DuplicateMapper duplicateMapper;


    @Override
    public PillItemDTO processPillItemDTO() {
        // 매퍼를 주입받아 고유값으로 select 쿼리를 실행한다.
        // 쿼리의 결과가 null이면 DB에 없는 책정보이므로 Return한다.
        // 만약 null이 아니면 DB에 있는 책 정보 이므로 return null 한다.
        int i = duplicateMapper.duplicatePill();
        if (item < i )

        return null
    }
}
