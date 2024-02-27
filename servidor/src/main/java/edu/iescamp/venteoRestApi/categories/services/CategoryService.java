package edu.iescamp.venteoRestApi.categories.services;

import edu.iescamp.venteoRestApi.categories.models.dto.CategoryDetailsDTO;
import edu.iescamp.venteoRestApi.categories.models.dto.CategoryOverviewDTO;
import edu.iescamp.venteoRestApi.categories.models.dto.CategoryPatchDTO;
import edu.iescamp.venteoRestApi.categories.repositories.entities.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryOverviewDTO> findAll();

    CategoryDetailsDTO findById(Long id);

    Category save(Category category);

    Category save(CategoryPatchDTO category);

    Boolean delete(Long id);
}
