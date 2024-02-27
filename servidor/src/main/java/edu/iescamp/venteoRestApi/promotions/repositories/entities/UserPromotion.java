package edu.iescamp.venteoRestApi.promotions.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users_promotions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPromotion {
    @EmbeddedId
    private UserPromotionId id;
}