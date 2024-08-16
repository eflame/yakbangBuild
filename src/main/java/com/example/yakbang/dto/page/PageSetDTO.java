package com.example.yakbang.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PageSetDTO {
    private int pageCount;
    private int startPage;
    private int endPage;
    private int realEnd;
    private boolean prev;
    private boolean next;
    private int total;
    private PageRequestDTO pageRequestDTO;

    public PageSetDTO(PageRequestDTO pageRequestDTO, int total) {
        this(pageRequestDTO, total, 5);
    }

    public PageSetDTO(PageRequestDTO pageRequestDTO, int total, int pageCount) {
        this.pageCount = pageCount;
        this.total = total;
        this.pageRequestDTO = pageRequestDTO;

//        현재 페이지를 기준으로 세트의 마지막 번호, 시작번호를 계산한다.
//        ceil() : 올림 처리
        this.endPage = (int)(Math.ceil(pageRequestDTO.getPage() / (double)pageCount)) * pageCount;
        this.startPage = endPage - pageCount + 1;

//        게시글 전체 개수로 실제 마지막 페이지를 구한다.
        this.realEnd = (int)Math.ceil((double)total / pageRequestDTO.getAmount());

//        세트의 마지막 번호보다 실제 마지막 페이지가 작다면?
        if(realEnd < endPage) {
//            세트의 마지막 번호를 실제 마지막 페이지로 변경해라
            this.endPage = realEnd == 0 ? 1 : realEnd;
        }

        this.prev = startPage > 1;
        this.next = endPage < realEnd;
    }
}
