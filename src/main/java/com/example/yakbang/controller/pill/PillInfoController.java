package com.example.yakbang.controller.pill;

import com.example.yakbang.dto.pill.PillListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import com.example.yakbang.dto.pill.PillDTO;
import com.example.yakbang.service.pill.PillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pill")
@Slf4j
@RequiredArgsConstructor
public class PillInfoController {

    private final PillService pillService;

    @GetMapping("/detail")
    public String pillDetail(@RequestParam("id") Long id, Model model) {
        // 서비스에서 id를 통해 약품 상세 정보 조회
        PillDTO pill = pillService.getPillById(id);
        model.addAttribute("pill", pill);

        // 상세 페이지 템플릿 반환
        return "pill/pill_detail";
    }

    @GetMapping("/list")
    public String getPills() {
        return "pill/pill_list";
    }


    

    @PostMapping("/searchResult")
    public String searchResult(String keyword, Model model) {
        List<PillListDTO> list;
        try {
                list = pillService.findPillData(keyword);
//            System.out.println("list = " + list);
//            System.out.println("검색 결과 개수"+list.size());
                model.addAttribute("pill", list);

        } catch (Exception e) {
            log.error(e.getMessage());
            return "pill/pill_list";
        }
        return "pill/pill_search_result_list";

    }



}

