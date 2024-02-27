package edu.iescamp.venteoRestApi.promotions.repositories.dao;

import edu.iescamp.venteoRestApi.promotions.repositories.entities.Promotion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Query("SELECT p FROM Promotion p WHERE p.code = :code")
    Promotion findByCode(String code);
}
