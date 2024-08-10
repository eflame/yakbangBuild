package com.example.yakbang.dto.pill;


import lombok.*;
import java.util.List;

@Getter @Setter @ToString
public class PillApiDTO<T> {
    private Body<T> body;

    @Getter @Setter @ToString
    @NoArgsConstructor @AllArgsConstructor
    public static class Body<T> {
        private int PageNo;
        private int totalCount;
        private int numberOfRows;
        private List<T> items;
    }

}
