package edu.iescamp.venteoRestApi.auctions.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AuctionPatchDTO {

    private Long auctionId;

    @Size(min = 3, max = 50, message = "El nombre de la subasta debe tener entre 3 y 50 caracteres")
    private String auctionName;

    @Size(min = 3, max = 255, message = "La descripción de la subasta debe tener entre 3 y 255 caracteres")
    private String auctionDescription;

    private Long categoryId;

    private Long userId;

    private Timestamp startsAt;

    private Timestamp endsAt;

    private String imageUrl;

    @Min(value = 0, message = "El precio inicial debe ser mayor que 0")
    private double initialPrice;
}
