package edu.iescamp.venteoRestApi.notifications.models.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class NotificationPatchDTO {

    private Long notificationId;

    private Long categoryId;

    @Size(min=3, max=50, message = "El campo subject debe tener entre 3 y 50 caracteres")
    private String subject;

    @Size(min=3, max=255, message = "El campo message debe tener entre 3 y 255 caracteres")
    private String message;

    private Timestamp sendOn;
}
