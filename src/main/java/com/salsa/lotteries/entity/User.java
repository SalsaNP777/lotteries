package com.salsa.lotteries.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String userName;
    @Column(name = "email")
    private String userEmail;
    @Column(name = "address")
    private String userAddress;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_lottery_id", referencedColumnName = "id")
    private Transaction transaction;
}
