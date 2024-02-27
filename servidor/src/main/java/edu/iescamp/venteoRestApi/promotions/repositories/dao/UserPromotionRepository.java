package edu.iescamp.venteoRestApi.promotions.repositories.dao;

import edu.iescamp.venteoRestApi.promotions.repositories.entities.UserPromotion;
import edu.iescamp.venteoRestApi.promotions.repositories.entities.UserPromotionId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserPromotionRepository extends JpaRepository<UserPromotion, UserPromotionId> {

}
