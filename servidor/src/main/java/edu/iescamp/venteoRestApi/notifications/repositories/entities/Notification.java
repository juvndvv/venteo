package edu.iescamp.venteoRestApi.notifications.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @NotNull(message = "El campo categoryId no puede ser nulo")
    private Long categoryId;

    @Size(min=1, max=50, message = "El campo subject debe tener entre 1 y 50 caracteres")
    @NotNull(message = "El campo subject no puede ser nulo")
    private String subject;

    @Size(min=1, max=255, message = "El campo message debe tener entre 1 y 255 caracteres")
    @NotNull(message = "El campo message no puede ser nulo")
    private String message;

    @NotNull(message = "El campo sendOn no puede ser nulo")
    private Timestamp sendOn;

}