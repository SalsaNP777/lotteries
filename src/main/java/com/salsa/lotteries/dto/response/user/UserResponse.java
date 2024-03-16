package com.salsa.lotteries.dto.response.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
//    is it necessary? no, bcs user don't need to input transactionId when they create account
//    private String transaction;
}
