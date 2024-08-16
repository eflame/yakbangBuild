package com.example.yakbang.api.board;

import com.example.yakbang.dto.board.ReviewPillSearchDTO;
import com.example.yakbang.service.board.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewApi {
    private final ReviewService reviewService;

    @GetMapping("/v1/pills/names")
    public List<ReviewPillSearchDTO> getPillList(String pillName){
        return reviewService.findPillListByName(pillName);
    }
}








