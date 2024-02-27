package edu.iescamp.venteoRestApi.auctions.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "auctions")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long auctionId;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 3, max = 50, message = "El nombre de la subasta debe tener entre 3 y 50 caracteres")
    private String auctionName;

    @NotNull(message = "La descripción no puede ser nula")
    @Size(min = 3, max = 255, message = "La descripción de la subasta debe tener entre 3 y 255 caracteres")
    private String auctionDescription;

    @NotNull(message = "La categoría no puede ser nula")
    private Long categoryId;

    @NotNull(message = "El usuario no puede ser nulo")
    private Long userId;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    private Timestamp startsAt;

    @NotNull(message = "La fecha de fin no puede ser nula")
    private Timestamp endsAt;

    @NotNull(message = "El precio inicial no puede ser nulo")
    @Min(value = 0, message = "El precio inicial debe ser mayor que 0")
    private Double initialPrice;

    @NotNull(message = "La imagen no puede ser nula")
    private String imageUrl;
}
