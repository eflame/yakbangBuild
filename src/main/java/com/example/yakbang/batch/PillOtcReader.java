package com.example.yakbang.batch;

import com.example.yakbang.dto.pill.PillOtcDTO;
import com.example.yakbang.service.pill.PillApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;

import java.util.List;

@RequiredArgsConstructor
public class PillOtcReader implements ItemReader<PillOtcDTO> {
    private final PillApiService pillApiService;

    private int nextIdx = 0;
    private List<PillOtcDTO> items;

    @Override
    public PillOtcDTO read() throws Exception {

        if (items == null) {
            List<PillOtcDTO> pillOtc = pillApiService.addPillOtc();
            items = pillOtc;
        }

        PillOtcDTO nextItemDTO = null;

        if (nextIdx < items.size()) {
            nextItemDTO = items.get(nextIdx);
            nextIdx++;
        } else {
            items = null;
            nextIdx = 0;
        }

        return nextItemDTO;
    }

}
