package edu.iescamp.venteoRestApi.notifications.repositories.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@IdClass(CategoriesPreferencesId.class)
@Data
@Table(name = "categories_preferences")
public class CategoriesPreferences {

    @Id
    private Long userId;

    @Id
    private Long categoryId;
}
