package edu.iescamp.venteoRestApi.categories.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long categoryId;

    @Size(min = 3, max = 100, message = "El nombre de la categoría debe tener entre 3 y 100 caracteres")
    @NotNull(message = "El nombre no puede ser nulo")
    public String name;

    @Size(min = 3, max = 255, message = "La descripción de la categoría debe tener entre 3 y 255 caracteres")
    @NotNull(message = "La descripción no puede ser nula")
    public String description;

    @NotNull(message = "La imagen no puede ser nula")
    public String imageUrl;
}
