package com.example.yakbang.service.pill;

import com.example.yakbang.dto.pill.PillApiDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class PillApiService {
    @Value("${api.pill.key}")
    private String apikey;
//    /1471000/DrbEasyDrugInfoService/getDrbEasyDrugList
//    /1471000/MdcinGrnIdntfcInfoService01/getMdcinGrnIdntfcInfoList01
    public PillApiDTO findPillData() {
        WebClient wc = WebClient.builder()
                .baseUrl("http://apis.data.go.kr")
                .build();

        PillApiDTO result = wc.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList")
                        .queryParam("serviceKey", apikey)
                        .queryParam("type", "json")
                        .queryParam("pageNo",1)
                        .queryParam("numOfRows", 100)
                        .build()
                ).retrieve()
                .bodyToMono(PillApiDTO.class)
                .block();

        log.info("result : {}", result);

        return result;
    }
}
