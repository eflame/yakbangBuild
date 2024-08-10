package com.example.yakbang.service.admin;

import com.example.yakbang.mapper.admin.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

    public Long findAdminId(String loginId, String password) {
        return adminMapper.selectAdminMemberId(loginId, password)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원 정보"));
    }

}
