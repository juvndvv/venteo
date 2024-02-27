package edu.iescamp.venteoRestApi.promotions.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionId;

    @NotNull(message = "El codigo no puede ser nulo")
    @Size(min = 3, max = 20, message = "El codigo debe tener entre 20 y 20 caracteres")
    private String code;

    @Column(name = "ends_at")
    private Timestamp endsAt;

    @NotNull(message = "La cantidad no puede ser nulo")
    @Size(min = 1, max = 5, message = "La cantidad debe tener entre 1 y 5 caracteres")
    private Float amount;

}
