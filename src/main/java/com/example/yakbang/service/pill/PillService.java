package com.example.yakbang.service.pill;

import com.example.yakbang.dto.pill.PageRequest;
import com.example.yakbang.dto.pill.PillDTO;
import com.example.yakbang.dto.pill.PillListDTO;
import com.example.yakbang.mapper.pill.PillMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
