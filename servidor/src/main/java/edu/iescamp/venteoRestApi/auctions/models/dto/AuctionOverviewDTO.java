package edu.iescamp.venteoRestApi.auctions.models.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AuctionOverviewDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String username;
    private Double initialPrice;
}
