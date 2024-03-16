package com.salsa.lotteries.dto.request.transaction;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionSearchRequest {
    private String user;
    private String lottery;
}
