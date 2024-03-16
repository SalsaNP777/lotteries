package com.salsa.lotteries.dto.response.transaction;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionResponse {
    private String id;
    private String user;
    private String lottery;
}
