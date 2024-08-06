package com.example.yakbang.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class PillPageController {

    @GetMapping("/pill_detail")
    public String pillDetail (){
        return "board/pill_detail";
    }

    @GetMapping("/pill_result")
    public String pillResult (){
        return "board/pill_result";
    }

}
