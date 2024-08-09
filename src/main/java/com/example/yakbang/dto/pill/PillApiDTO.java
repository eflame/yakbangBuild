package com.example.yakbang.dto.pill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class PillApiDTO<T> {
    private Body<T> body;

    @Getter @Setter @ToString
    public static class Body<T> {
        private int PageNo;
        private int totalCount;
        private int numberOfRows;
        private List<T> items;
    }

    public Body<T> getBody() {
        return body;
    }
}
