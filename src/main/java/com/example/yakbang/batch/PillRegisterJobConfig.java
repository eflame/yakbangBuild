package com.example.yakbang.batch;

import com.example.yakbang.dto.pill.PillApiDTO;
import com.example.yakbang.dto.pill.PillItemDTO;
import com.example.yakbang.mapper.duplicate.DuplicateMapper;
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
public class PillRegisterJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final PillMapper pillMapper;
    private final PillApiService pillApiService;
    private final DuplicateMapper duplicateMapper;

    @Bean
    public ItemReader<PillItemDTO> apiItemReader(){
        return new PillItemReader(pillApiService);
    }

    @Bean
    public ItemProcessor<PillItemDTO, PillItemDTO> apiItemProcessor(){
        return new PillItemProcessor(duplicateMapper);
    }

    @Bean
    public ItemWriter<PillItemDTO> apiItemWriter(){
        return items -> items.forEach(pillMapper::insertPill);
    }

    @Bean
    public Step apiStep(){
        return new StepBuilder("apiStep", jobRepository)
                .<PillItemDTO, PillItemDTO>chunk(100, transactionManager)
                .reader(apiItemReader())
                .processor(apiItemProcessor())
                .writer(apiItemWriter())
                .build();
    }

    @Bean
    public Job apiJob(){
        return new JobBuilder("apiJob", jobRepository)
                .start(apiStep())
                .preventRestart()
                .build();
    }

}
