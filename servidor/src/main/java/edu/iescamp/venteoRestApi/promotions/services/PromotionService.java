package edu.iescamp.venteoRestApi.promotions.services;

import edu.iescamp.venteoRestApi.promotions.models.dto.PromotionDto;
import edu.iescamp.venteoRestApi.promotions.models.dto.UseFormDTO;
import edu.iescamp.venteoRestApi.promotions.repositories.entities.Promotion;
import edu.iescamp.venteoRestApi.promotions.repositories.entities.UserPromotion;

import java.util.List;

public interface PromotionService {

    List<Promotion> findAll();

    Promotion findById(Long id);

    Promotion save (Promotion promotion);

    void update (Promotion promotion);

    void updatePatch (PromotionDto promotionDto);

    void delete (Long id);

    Boolean use (UseFormDTO useFormDTO);

}