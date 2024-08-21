package com.example.yakbang.controller.pill;

import com.example.yakbang.dto.pill.PageRequest;
import com.example.yakbang.dto.pill.PillListDTO;
import com.example.yakbang.service.pill.PillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PillRestController {
    private final PillService pillService;

    @GetMapping("/v1/pills")
    public List<PillListDTO> getPills(PageRequest pageRequest){

        return pillService.findListWithPage(pageRequest);
    }
}











