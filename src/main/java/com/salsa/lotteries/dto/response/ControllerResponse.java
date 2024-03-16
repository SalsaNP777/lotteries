package com.salsa.lotteries.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ControllerResponse<T>   {
    private String status;
    private String message;
    private T data;
}
