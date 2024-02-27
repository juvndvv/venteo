package edu.iescamp.venteoRestApi.notifications.services.impl;

import edu.iescamp.venteoRestApi.notifications.exceptions.NotificationNotFoundException;
import edu.iescamp.venteoRestApi.notifications.models.dto.NotificationPatchDTO;
import edu.iescamp.venteoRestApi.notifications.repositories.dao.NotificationRepository;
import edu.iescamp.venteoRestApi.notifications.repositories.entities.Notification;
import edu.iescamp.venteoRestApi.notifications.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> findByUserId(Long userId) {
        return notificationRepository.findNotificationsByUserId(userId);
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public void updateNotification(Notification notification) {
        if(notificationRepository.findById(notification.getNotificationId()).isEmpty()){
            throw new NotificationNotFoundException(notification.getNotificationId());
        }
        notificationRepository.save(notification);
    }

    @Override
    public void patchNotification(NotificationPatchDTO notificationPatchDTO) {
        Notification notification = notificationRepository.findById(notificationPatchDTO.getNotificationId()).orElseThrow(() -> new NotificationNotFoundException(notificationPatchDTO.getNotificationId()));
        if(notificationPatchDTO.getCategoryId() != null){
            notification.setCategoryId(notificationPatchDTO.getCategoryId());
        }
        if(notificationPatchDTO.getSubject() != null){
            notification.setSubject(notificationPatchDTO.getSubject());
        }
        if(notificationPatchDTO.getMessage() != null){
            notification.setMessage(notificationPatchDTO.getMessage());
        }
        if(notificationPatchDTO.getSendOn() != null){
            notification.setSendOn(notificationPatchDTO.getSendOn());
        }
        notificationRepository.save(notification);
    }
}
