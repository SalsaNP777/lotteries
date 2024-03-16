package com.salsa.lotteries.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "lottery_id", referencedColumnName = "id")
    private Lottery lottery;
    private String winner;
}
