package com.example.yakbang.controller.admin;

import com.example.yakbang.dto.admin.AdminDTO;
import com.example.yakbang.dto.admin.AdminPillDTO;
import com.example.yakbang.dto.admin.AdminQnaDTO;
import com.example.yakbang.dto.admin.AdminReviewDTO;
import com.example.yakbang.service.admin.AdminBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/board")
@RequiredArgsConstructor
public class AdminBoardController {
    private final AdminBoardService adminBoardService;

    @GetMapping("/qna/{questionId}")
    public ResponseEntity<List<AdminQnaDTO>> findQnaInfo(@PathVariable("questionId") Long questionId) {
        List<AdminQnaDTO> findQnaDetail = adminBoardService.findQnaInfo(questionId);
        return ResponseEntity.ok(findQnaDetail);
    }

    @DeleteMapping("/delete/qna/{questionId}")
    public ResponseEntity<Map<String, Object>> deleteQna(@PathVariable("questionId") Long questionId) {
        Map<String, Object> response = new HashMap<>();

        boolean success = adminBoardService.deleteQna(questionId) > 0;

        response.put("success", success);

        if (success) {
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "전문 회원 실패");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<List<AdminReviewDTO>> findReviewInfo(@PathVariable("reviewId") Long reviewId) {
        System.out.println("reviewId = " + reviewId);
        List<AdminReviewDTO> findReviewDetail = adminBoardService.findReviewInfo(reviewId);
        return ResponseEntity.ok(findReviewDetail);
    }

    @DeleteMapping("/delete/review/{reviewId}")
    public ResponseEntity<Map<String, Object>> deleteReview(@PathVariable("reviewId") Long reviewId) {
        Map<String, Object> response = new HashMap<>();

        boolean success = adminBoardService.deleteReview(reviewId) > 0;

        response.put("success", success);

        if (success) {
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "전문 회원 실패");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
