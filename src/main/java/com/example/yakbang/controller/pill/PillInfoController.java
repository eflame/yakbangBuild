package com.example.yakbang.controller.pill;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pill")
public class PillInfoController {

    @GetMapping("/detail")
    public String pillDetail (){
        return "pill/pill_detail";
    }

    @GetMapping("/list")
    public String pillList (){
        return "pill/pill_list";
    }

}
