package edu.iescamp.venteoRestApi.categories.repositories.dao;

import edu.iescamp.venteoRestApi.categories.repositories.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
