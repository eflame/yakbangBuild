package com.example.yakbang.dto.pill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
public class PillApiDTO {
    private Body body;
    @Getter @Setter @ToString
    public static class Body {
        private int PageNo;
        private int totalCount;
        private int numberOfRows;
        private List<PillItemDTO> items;

    }
}
