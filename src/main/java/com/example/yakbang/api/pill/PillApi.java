package com.example.yakbang.api.pill;

import com.example.yakbang.batch.PillOtcRegisterJobConfig;
import com.example.yakbang.batch.PillRegisterJobConfig;
import com.example.yakbang.service.pill.PillApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class PillApi {
    private final PillApiService pillApiService;

    private final JobLauncher jobLauncher;
    private final PillRegisterJobConfig pillRegisterJobConfig;
    private final PillOtcRegisterJobConfig pillOtcRegisterJobConfig;

    @GetMapping("/batch")
    public void batch() throws URISyntaxException, UnsupportedEncodingException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(pillRegisterJobConfig.apiJob(), jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/add-batch")
    public void addBatch() throws URISyntaxException, UnsupportedEncodingException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(pillOtcRegisterJobConfig.addJob(), jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/test")
    public String test(){
        try {
            pillApiService.findPillData();
//            pillApiService.addPillOtc();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return "test";
    }
}
