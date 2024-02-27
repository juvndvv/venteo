package edu.iescamp.venteoRestApi.categories.services.impl;

import edu.iescamp.venteoRestApi.auctions.services.AuctionService;
import edu.iescamp.venteoRestApi.categories.exceptions.CategoryNotFoundException;
import edu.iescamp.venteoRestApi.categories.models.dto.CategoryDetailsDTO;
import edu.iescamp.venteoRestApi.categories.models.dto.CategoryOverviewDTO;
import edu.iescamp.venteoRestApi.categories.models.dto.CategoryPatchDTO;
import edu.iescamp.venteoRestApi.categories.models.mappers.CategoryMapper;
import edu.iescamp.venteoRestApi.categories.repositories.dao.CategoryRepository;
import edu.iescamp.venteoRestApi.categories.repositories.entities.Category;
import edu.iescamp.venteoRestApi.categories.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryOverviewDTO> findAll() {
        Iterable<Category> categories = categoryRepository.findAll();

        List<CategoryOverviewDTO> categoriesList = new ArrayList<>();
        categories.forEach(category -> categoriesList.add(categoryMapper.toOverviewDTO(category)));

        return categoriesList;
    }

    @Override
    public CategoryDetailsDTO findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryMapper.toDetailsDTO(category, auctionService.findAuctionsByCategoryId(id));
    }

    @Override
    public Category save(Category category) {
        // Si se está actualizando, se comprueba que exista
        if (category.getCategoryId() != null) {
            findById(category.getCategoryId()); // Si no lo encuentra lanza CategoryNotFoundException
        }

        return categoryRepository.save(category);
    }

    @Override
    public Category save(CategoryPatchDTO category) {
        // Busca la categoría a actualizar
        Category categoryToUpdate = categoryRepository.findById(
                category.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(category.getCategoryId()));

        updateCategory(categoryToUpdate, category);

        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    public Boolean delete(Long id) {
        findById(id);   // Comprueba que exista. Si no -> CategoryNotFoundException
        categoryRepository.deleteById(id);
        return true;
    }

    private void updateCategory(Category categoryToUpdate, CategoryPatchDTO category) {
        // Actualiza los campos que se hayan recibido
        if (category.getName() != null) {
            categoryToUpdate.setName(category.getName());
        }

        if (category.getDescription() != null) {
            categoryToUpdate.setDescription(category.getDescription());
        }

        if (category.getImageUrl() != null) {
            categoryToUpdate.setImageUrl(category.getImageUrl());
        }
    }
}
