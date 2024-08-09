package com.example.yakbang.batch;

import com.example.yakbang.dto.pill.PillItemDTO;
import com.example.yakbang.dto.pill.PillOtcDTO;
import com.example.yakbang.mapper.pill.PillMapper;
import com.example.yakbang.service.pill.PillApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PillOtcRegisterJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final PillMapper pillMapper;
    private final PillApiService pillApiService;

    @Bean
    public ItemReader<PillOtcDTO> addItemReader () {
        return new PillOtcReader(pillApiService);
    }

    @Bean
    public ItemProcessor<PillOtcDTO, PillOtcDTO> addItemProcessor () {
        return item -> item;
    }

    @Bean
    public ItemWriter<PillOtcDTO> addItemWriter () {
        return items -> {
            for (PillOtcDTO item : items) {
                pillMapper.updatePill(item);
            }
        };
    }

    @Bean
    public Step addStep() {
        return new StepBuilder("addStep", jobRepository)
                .<PillOtcDTO, PillOtcDTO>chunk(10, transactionManager)
                .reader(addItemReader())
                .processor(addItemProcessor())
                .writer(addItemWriter())
                .build();
    }

    @Bean
    public Job addJob(){
        return new JobBuilder("addJob", jobRepository)
                .start(addStep())
                .preventRestart()
                .build();
    }


}
