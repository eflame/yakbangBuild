package com.example.yakbang.batch;

import com.example.yakbang.dto.pill.PillApiDTO;
import com.example.yakbang.dto.pill.PillItemDTO;
import com.example.yakbang.service.pill.PillApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

@RequiredArgsConstructor
public class PillItemReader implements ItemReader<PillItemDTO> {
    private final PillApiService pillApiService;

    private int nextIdx = 0;
    private List<PillItemDTO> items;

    @Override
    public PillItemDTO read() throws  Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (items == null) {
            PillApiDTO pillApiDTO = pillApiService.findPillData();
            items = pillApiDTO.getBody().getItems();
        }

        PillItemDTO nextItemDTO = null;

        if (nextIdx >= items.size()) {
            nextItemDTO = items.get(nextIdx);
            nextIdx++;
        } else {
            items = null;
            nextIdx = 0;
        }

        return nextItemDTO;
    }

}
