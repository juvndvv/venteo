package edu.iescamp.venteoRestApi.auctions.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bids")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidId;
    private Long auctionId;
    private Long userId;
    private Double amount;
}
