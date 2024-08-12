package com.example.yakbang.service.admin;

import com.example.yakbang.dto.admin.AdminPillDTO;
import com.example.yakbang.mapper.admin.AdminPillMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminPillService {

    private final AdminPillMapper adminPillMapper;

    public List<AdminPillDTO> findPillInfo(Long itemSeq){
        return adminPillMapper.selectPillInfo(itemSeq);
    }

}
