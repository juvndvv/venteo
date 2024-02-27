package edu.iescamp.venteoRestApi.promotions.web.controllers;

import edu.iescamp.venteoRestApi.promotions.models.dto.PromotionDto;
import edu.iescamp.venteoRestApi.promotions.models.dto.UseFormDTO;
import edu.iescamp.venteoRestApi.promotions.repositories.entities.Promotion;
import edu.iescamp.venteoRestApi.promotions.repositories.entities.UserPromotion;
import edu.iescamp.venteoRestApi.promotions.repositories.entities.UserPromotionId;
import edu.iescamp.venteoRestApi.promotions.services.PromotionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/promotions")
    public List<Promotion> findAll() {
        return promotionService.findAll();
    }

    @PostMapping("/promotions")
    public Promotion save(@Valid @RequestBody Promotion promotion){
        return promotionService.save(promotion);
    }

    @GetMapping("/promotion/{id}")
    public Promotion findById(@PathVariable Long id){
        return promotionService.findById(id);
    }

    @PostMapping("/promotion/{id}")
    public void update(@Valid @RequestBody Promotion promotion, @PathVariable Long id) {
        promotion.setPromotionId(id);
        promotionService.update(promotion);
    }

    @PatchMapping("/promotion/{id}")
    public void patch(@Valid @RequestBody PromotionDto promotionDto, @PathVariable Long id) {
        promotionDto.setPromotionId(id);
        promotionService.updatePatch(promotionDto);
    }

    @PostMapping("/promotion/use")
    public Boolean use(@RequestBody UseFormDTO useFormDTO) {
        return promotionService.use(useFormDTO);
    }

}
