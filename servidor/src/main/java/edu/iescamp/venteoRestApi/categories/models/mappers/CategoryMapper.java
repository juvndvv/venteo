package edu.iescamp.venteoRestApi.categories.models.mappers;

import edu.iescamp.venteoRestApi.auctions.repositories.entities.Auction;
import edu.iescamp.venteoRestApi.categories.models.dto.CategoryDetailsDTO;
import edu.iescamp.venteoRestApi.categories.models.dto.CategoryOverviewDTO;
import edu.iescamp.venteoRestApi.categories.repositories.entities.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public CategoryOverviewDTO toOverviewDTO(Category category) {
        return CategoryOverviewDTO.builder().
                categoryId(category.getCategoryId()).
                name(category.getName()).
                description(category.getDescription()).
                imageUrl(category.getImageUrl()).
                build();
    }

    public CategoryDetailsDTO toDetailsDTO(Category category, List<Auction> auctions) {
        return CategoryDetailsDTO.builder().
                categoryId(category.getCategoryId()).
                name(category.getName()).
                description(category.getDescription()).
                imageUrl(category.getImageUrl()).
                auctions(auctions).
                build();
    }
}
