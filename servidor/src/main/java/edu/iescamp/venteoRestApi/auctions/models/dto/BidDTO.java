package edu.iescamp.venteoRestApi.auctions.models.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BidDTO {
    private String userName;
    private String imageUrl;
    private Double amount;
}
