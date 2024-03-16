package com.salsa.lotteries.dto.response.lottery;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LotteryResponse {
    private String id;
    private String name;
}
