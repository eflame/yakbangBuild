package com.example.yakbang.batch;

import com.example.yakbang.dto.pill.PillItemDTO;
import com.example.yakbang.mapper.duplicate.DuplicateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class PillItemProcessor implements ItemProcessor<PillItemDTO, PillItemDTO> {
    private final DuplicateMapper duplicateMapper;


    @Override
    public PillItemDTO process(PillItemDTO item) throws Exception {
        int count = duplicateMapper.duplicatePill(item.getItemSeq());
        if (count > 0) {
            return null;
        }

        return item;
    }
}
