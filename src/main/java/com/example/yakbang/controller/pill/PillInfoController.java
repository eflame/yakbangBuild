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

<<<<<<< HEAD


=======
    @GetMapping("/searchResult")
    public String searchResult() {

        return "pill/pill_search_result_list";
    }

    @PostMapping("/searchResult")
    public String searchResult(String keyword, Model model) {
        List<PillListDTO> list;
        try {
            if (pillService.findPillData("keyword") == null) {
                list = pillService.findPillItemList(keyword);
                model.addAttribute("list", list);
            } else{
                list = pillService.findPillData(keyword);
                model.addAttribute("list", list);
            }
        } catch (Exception e) {
            log.error(e.toString());
            System.out.println("오류발생!!");
        }




        return "pill/pill_search_result_list";
    }
>>>>>>> work7
}

