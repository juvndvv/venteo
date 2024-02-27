package edu.iescamp.venteoRestApi.issues.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "issues")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    @NotNull(message = "El asunto no puede ser nulo")
    @Size(min = 3, max = 50, message = "El asunto de la incidencia debe de contener entre 3 y 50 caracteres")
    private String subject;

    @NotNull(message = "El mensaje no puede ser nulo")
    @Size(min = 3, max = 255, message = "El mensaje de la incidencia debe de contener entre 3 y 255 caracteres")
    private String message;

    @NotNull(message = "El campo createdAt no puede ser nulo")
    private Timestamp createdAt;

    @NotNull(message = "El campo isSolved no puede ser nulo")
    private Boolean isSolved;

    @NotNull(message = "El id no puede ser nulo")
    private Long userId;

}