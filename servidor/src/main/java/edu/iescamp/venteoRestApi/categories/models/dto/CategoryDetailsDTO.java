package edu.iescamp.venteoRestApi.categories.models.dto;

import edu.iescamp.venteoRestApi.auctions.repositories.entities.Auction;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDetailsDTO {
    private Long categoryId;
    private String name;
    private String description;
    private String imageUrl;
    private List<Auction> auctions;
}
