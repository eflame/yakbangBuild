package com.example.yakbang.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PageRequestDTO {
    private int page; // 현재 페이지
    private int amount; // 한 페이지 당 게시물 수

    //    Spring MVC를 사용하여 웹 개발을 진행하면 Controller, RestController 같은 편리한 기능을 활용할 수 있다.
    //    Controller의 매개변수로 선언한 DTO 객체들은 기본적으로 기본생성자를 활용하여 생성되고
    //    setter로 데이터를 수정한다.
    //    즉, 기본생성자에서 데이터를 수정한다면 쿼리스트링으로 데이터를 보내지 않아도 기본 데이터를 가지게 되는것이다.
    public PageRequestDTO() {
        this.page = 1;
        this.amount = 10;
    }
}
