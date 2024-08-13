package com.example.yakbang.controller.pill;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import com.example.yakbang.dto.pill.PillDTO;
import com.example.yakbang.service.pill.PillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pill")
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
    public String getPills(Model model) {
//        List<PillDTO> pills = pillService.getPillsInfo();
//        model.addAttribute("pills", pills);
        return "pill/pill_list";
    }

}

