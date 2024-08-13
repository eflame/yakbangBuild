package com.example.yakbang.dto.pill;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class PillListDTO {
    private Long itemSeq;
    private String pillImage;
    private String companyName;
    private String pillName;
}
