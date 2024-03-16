package com.salsa.lotteries.dto.request.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateRequest {
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
}
