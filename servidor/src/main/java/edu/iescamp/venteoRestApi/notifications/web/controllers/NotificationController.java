package edu.iescamp.venteoRestApi.notifications.web.controllers;

import edu.iescamp.venteoRestApi.notifications.models.dto.NotificationPatchDTO;
import edu.iescamp.venteoRestApi.notifications.repositories.entities.Notification;
import edu.iescamp.venteoRestApi.notifications.services.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @GetMapping("/notifications")
    public List<Notification> findNotifications(@RequestParam(required = false) Long userId) {
        if(userId != null){
            return notificationService.findByUserId(userId);
        }else{
            return notificationService.findAll();
        }
    }

    @PostMapping("/notifications")
    public void saveNotification(@Valid @RequestBody Notification notification) {
        notificationService.saveNotification(notification);
    }

    @PutMapping("/notification/{id}")
    public void updateNotification(@Valid @RequestBody Notification notification, @PathVariable Long id) {
        notification.setNotificationId(id);
        notificationService.updateNotification(notification);
    }
    @PatchMapping("/notification/{id}")
    public void updateNotificationPatch(@Valid @RequestBody NotificationPatchDTO notificationPatchDTO, @PathVariable Long id) {
        notificationPatchDTO.setNotificationId(id);
        notificationService.patchNotification(notificationPatchDTO);
    }

    @DeleteMapping("/notification/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }
}
