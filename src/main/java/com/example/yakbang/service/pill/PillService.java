package com.example.yakbang.service.pill;

import com.example.yakbang.dto.pill.PillDTO;
import com.example.yakbang.mapper.pill.PillMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PillService {
    private final PillMapper pillMapper;

    public List<PillDTO> getPillsInfo()  {
        return pillMapper.findAll();
    }
    public PillDTO getPillById(Long id) {
        return pillMapper.findById(id);
    }
}
