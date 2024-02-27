package edu.iescamp.venteoRestApi.promotions.models.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PromotionDto {

    private Long promotionId;

    private String code;

    private Timestamp endsAt;

    private Float amount;
}
