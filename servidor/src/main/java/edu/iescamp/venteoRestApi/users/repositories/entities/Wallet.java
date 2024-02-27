package edu.iescamp.venteoRestApi.users.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "WALLETS")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;

    private Long userId;

    private Double amount;
}
