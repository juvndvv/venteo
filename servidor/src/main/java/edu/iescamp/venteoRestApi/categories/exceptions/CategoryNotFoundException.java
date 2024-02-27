package edu.iescamp.venteoRestApi.categories.exceptions;

import edu.iescamp.venteoRestApi.shared.exceptions.DataNotFoundException;

public class CategoryNotFoundException extends DataNotFoundException {
    public CategoryNotFoundException(Long id) {
        super("La categoría con id " + id + " no existe");
    }
}
