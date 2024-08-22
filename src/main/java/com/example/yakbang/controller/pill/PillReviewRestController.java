package com.example.yakbang.controller.pill;

import com.example.yakbang.dto.board.ReviewListDTO;
import com.example.yakbang.dto.pill.PageRequest;
import com.example.yakbang.service.board.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PillReviewRestController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public List<ReviewListDTO> getReviews(String itemSeq, PageRequest pageRequest) {
        return reviewService.findReviewInPillDetail(itemSeq, pageRequest);
    }
}
