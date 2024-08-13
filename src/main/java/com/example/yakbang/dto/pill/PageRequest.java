package com.example.yakbang.dto.pill;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PageRequest {
    private int page;
    private int amount;

    public PageRequest() {
        this.page = 1;
        this.amount = 10;
    }
}
