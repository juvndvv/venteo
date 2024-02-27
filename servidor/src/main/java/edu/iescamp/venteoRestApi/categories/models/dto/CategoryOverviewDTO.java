package edu.iescamp.venteoRestApi.categories.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryOverviewDTO {
    private Long categoryId;
    private String name;
    private String description;
    private String imageUrl;
}
