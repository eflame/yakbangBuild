package com.example.yakbang.service.pill;

import com.example.yakbang.dto.pill.PillApiDTO;
import com.example.yakbang.dto.pill.PillItemDTO;
import com.example.yakbang.dto.pill.PillOtcDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class PillApiService {
    @Value("${api.pill.key}")
    private String apiKey;
    String baseUrl = "http://apis.data.go.kr";

    public List<PillItemDTO> findPillData() throws UnsupportedEncodingException, URISyntaxException {
        String encodeKey = URLEncoder.encode(apiKey, "UTF-8");
        String path = "/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
        int pageNo = 1;

        List<PillItemDTO> resultList = new ArrayList<>();

        while (true) {
            URI uri = new URI(baseUrl + path + "?serviceKey=" + encodeKey +
                    "&type=json" + "&numOfRows=" + 80 + "&pageNo=" + pageNo);

            WebClient wc = WebClient.builder().build();

            PillApiDTO<PillItemDTO> pillApiDTO = wc.get() // 요청방식을 설정
                    .uri(uri)
                    .retrieve() // 응답을 어떻게 받을지 설정 (응답 본문만 간단히 받겠다는 의미)
                    .bodyToMono(new ParameterizedTypeReference<PillApiDTO<PillItemDTO>>() {
                    }) // 응답 본문을 Mono<String>으로 변환한다.
                    .block();// 블로킹 방식으로 통신을 하겠다.

            List<PillItemDTO> items = pillApiDTO.getBody().getItems();
            pageNo++;
            if (items == null) {
                break;
            }

            log.info("Pill Data: {}", items);
            resultList.addAll(items);
        }
        return resultList;
    }


    public List<PillOtcDTO> addPillOtc() throws UnsupportedEncodingException, URISyntaxException {
        String encodeKey = URLEncoder.encode(apiKey, "UTF-8");
        String path = "/1471000/MdcinGrnIdntfcInfoService01/getMdcinGrnIdntfcInfoList01";
        int pageNo = 1;

        List<PillOtcDTO> resultList = new ArrayList<>();

        while (true) {
            URI uri = new URI(baseUrl + path + "?serviceKey=" + encodeKey  +
                    "&type=json" + "&numOfRows=" + 200 + "&pageNo=" + pageNo);
            WebClient wc = WebClient.builder().build();
            PillApiDTO<PillOtcDTO> pillApiDTO = wc.get() // 요청방식을 설정
                    .uri(uri)
                    .retrieve() // 응답을 어떻게 받을지 설정 (응답 본문만 간단히 받겠다는 의미)
                    //.bodyToMono(PillApiDTO.class) // 응답 본문을 Mono<String>으로 변환한다.
                    .bodyToMono(new ParameterizedTypeReference<PillApiDTO<PillOtcDTO>>() {
                    })
                    .block();// 블로킹 방식으로 통신을 하겠다.

            List<PillOtcDTO> items = pillApiDTO.getBody().getItems();
            pageNo++;

            if(items == null){
                break;
            }

            resultList.addAll(items);
        }
        return resultList;
    }
}
