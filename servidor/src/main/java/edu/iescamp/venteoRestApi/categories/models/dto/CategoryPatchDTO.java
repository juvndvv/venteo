package edu.iescamp.venteoRestApi.categories.models.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryPatchDTO {
    private Long categoryId;
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;

    @Size(min = 3, max = 255, message = "La descripción debe tener entre 3 y 255 caracteres")
    private String description;

    @Size(min = 3, max = 255, message = "La imagen debe tener entre 3 y 255 caracteres")
    private String imageUrl;
}
