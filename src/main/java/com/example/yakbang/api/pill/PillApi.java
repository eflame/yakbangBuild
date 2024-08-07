package com.example.yakbang.api.pill;

import com.example.yakbang.batch.PillRegisterJobConfig;
import com.example.yakbang.service.pill.PillApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PillApi {
    private final PillApiService pillApiService;

    private final JobLauncher jobLauncher;
    private final PillRegisterJobConfig pillRegisterJobConfig;


    @GetMapping("/test")
    public String test(){
        pillApiService.findPillData();
        return "test";
    }
}
