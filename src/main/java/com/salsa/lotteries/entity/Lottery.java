package com.salsa.lotteries.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lottery")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Lottery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "lottery_name")
    private String lotteryName;
    @ManyToOne
    @JoinColumn(name = "user_lottery_id", referencedColumnName = "id")
    private Transaction transaction;
}
