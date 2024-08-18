package com.example.yakbang.controller.admin;

import com.example.yakbang.dto.admin.AdminPillDTO;
import com.example.yakbang.dto.admin.AdminQnaDTO;
import com.example.yakbang.service.admin.AdminBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/board")
@RequiredArgsConstructor
public class AdminBoardController {
    private final AdminBoardService adminBoardService;

    @GetMapping("/{questionId}")
    public ResponseEntity<List<AdminQnaDTO>> findQnaInfo(@PathVariable("questionId") Long questionId) {
        List<AdminQnaDTO> findQnaDetail = adminBoardService.findQnaInfo(questionId);
        return ResponseEntity.ok(findQnaDetail);
    }

}
