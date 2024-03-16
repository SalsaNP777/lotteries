package com.salsa.lotteries.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PageResponseWrapper <T>{
    private T data;
    private Long totalElement;
    private Integer totalPage;
    private Integer currentPage;
    private Integer size;
}
