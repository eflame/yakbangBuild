package com.example.yakbang.schedule;

import com.example.yakbang.batch.PillRegisterJobConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiBatchSchedule {
    private final JobLauncher jobLauncher;
    private final PillRegisterJobConfig pillRegisterJobConfig;

    @Scheduled(cron = "0 0 3 * * *")
    private void pillRegister() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(pillRegisterJobConfig.apiJob(), jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
