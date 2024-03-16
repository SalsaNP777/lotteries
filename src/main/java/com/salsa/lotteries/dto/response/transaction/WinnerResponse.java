package com.salsa.lotteries.dto.response.transaction;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WinnerResponse {

    private String transactionId;
    private String lotteryId;
    private String lotteryName;
    private String winner;
}
