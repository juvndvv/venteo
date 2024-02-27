package edu.iescamp.venteoRestApi.promotions.repositories.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Embeddable
public class UserPromotionId {
    private Long promotionId;
    private Long userId;
}
