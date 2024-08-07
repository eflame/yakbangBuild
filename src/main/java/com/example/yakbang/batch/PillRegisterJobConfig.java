package com.example.yakbang.batch;

import com.example.yakbang.dto.pill.PillApiDTO;
import com.example.yakbang.dto.pill.PillItemDTO;
import com.example.yakbang.mapper.pill.PillMapper;
import com.example.yakbang.service.pill.PillApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
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

    @Bean
    public ItemReader<PillItemDTO> apiItemReader(){
        return new PillItemReader(pillApiService);
    }

    @Bean
    public ItemProcessor<PillItemDTO, PillItemDTO> apiItemProcessor(){
        return item -> item;
    }
}
