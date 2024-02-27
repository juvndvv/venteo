package edu.iescamp.venteoRestApi.promotions.services.impl;

import edu.iescamp.venteoRestApi.promotions.exceptions.PromotionNotFoundException;
import edu.iescamp.venteoRestApi.promotions.models.dto.PromotionDto;
import edu.iescamp.venteoRestApi.promotions.models.dto.UseFormDTO;
import edu.iescamp.venteoRestApi.promotions.repositories.dao.PromotionRepository;
import edu.iescamp.venteoRestApi.promotions.repositories.dao.UserPromotionRepository;
import edu.iescamp.venteoRestApi.promotions.repositories.entities.Promotion;
import edu.iescamp.venteoRestApi.promotions.repositories.entities.UserPromotion;
import edu.iescamp.venteoRestApi.promotions.repositories.entities.UserPromotionId;
import edu.iescamp.venteoRestApi.promotions.services.PromotionService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private UserPromotionRepository userPromotionRepository;

    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion findById(Long id) {
        return promotionRepository.findById(id).orElseThrow(() -> new PromotionNotFoundException(id));
    }

    @Override
    public Promotion save(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public void update(Promotion promotion) {
        if (promotionRepository.findById(promotion.getPromotionId()).isEmpty()) {
            throw new PromotionNotFoundException(promotion.getPromotionId());
        }
        promotionRepository.save(promotion);
    }

    @Override
    public void updatePatch(PromotionDto promotionDto) {

        Promotion promotion = promotionRepository.findById(promotionDto.getPromotionId()).orElseThrow(() -> new PromotionNotFoundException(promotionDto.getPromotionId()));
        if (promotionDto.getCode() != null){
            promotion.setCode(promotionDto.getCode());
        }
        if (promotionDto.getEndsAt() != null){
            promotion.setEndsAt(promotionDto.getEndsAt());
        }
        if (promotionDto.getAmount() != null){
            promotion.setAmount(promotionDto.getAmount());
        }
        promotionRepository.save(promotion);
    }

    @Override
    public void delete(Long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public Boolean use(UseFormDTO useFormDTO) {
        System.out.println(useFormDTO.getCode());
        Promotion promotion = promotionRepository.findByCode(useFormDTO.getCode());
        System.out.println(promotion);

        if (promotion == null) {
            return false;
        }

        UserPromotion userPromotion = new UserPromotion();
        UserPromotionId userPromotionId = new UserPromotionId();
        userPromotionId.setPromotionId(promotion.getPromotionId());
        userPromotionId.setUserId(useFormDTO.getUserId());

        userPromotion.setId(userPromotionId);

        userPromotionRepository.save(userPromotion);
        return true;
    }

    private boolean exists(UserPromotion userPromotion) {
        return userPromotionRepository.findById(userPromotion.getId()).isPresent();
    }

}
