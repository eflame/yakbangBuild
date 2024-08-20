package com.example.yakbang.api.board;

import com.example.yakbang.dto.board.ReviewListDTO;
import com.example.yakbang.dto.board.ReviewPillSearchDTO;
import com.example.yakbang.dto.pill.PageRequest;
import com.example.yakbang.service.board.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewApi {
    private final ReviewService reviewService;

    @GetMapping("/v1/pills/names")
    public List<ReviewPillSearchDTO> getPillList(String pillName){
        return reviewService.findPillListByName(pillName);
    }

    @GetMapping("/v1/reviews")
    public List<ReviewListDTO> getReviewList(String keyword, PageRequest pageRequest){
//        log.info("keyword = {}, pageRequest = {}", keyword, pageRequest);
        List<ReviewListDTO> listWithPage = reviewService.findListWithPage(keyword, pageRequest);
//        log.info("listWithPage = {}", listWithPage);
        return listWithPage;
    }
}








