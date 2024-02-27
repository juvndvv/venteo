package edu.iescamp.venteoRestApi.notifications.repositories.dao;

import edu.iescamp.venteoRestApi.notifications.repositories.entities.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query(value = "SELECT n FROM Notification n WHERE n.categoryId in (SELECT p.categoryId FROM CategoriesPreferences p WHERE p.userId = ?1)")
    List<Notification> findNotificationsByUserId(Long userId);
}
