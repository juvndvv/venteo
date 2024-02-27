package edu.iescamp.venteoRestApi.notifications.services;

import edu.iescamp.venteoRestApi.notifications.models.dto.NotificationPatchDTO;
import edu.iescamp.venteoRestApi.notifications.repositories.entities.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NotificationService {

    List<Notification> findByUserId(Long userId);

    List<Notification> findAll();

    void deleteNotification(Long id);

    void saveNotification(Notification notification);

    void updateNotification(Notification notification);

    void patchNotification(NotificationPatchDTO notificationPatchDTO);
}
