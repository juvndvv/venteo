package edu.iescamp.venteoRestApi.issues.models.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class IssueDTO {

    private Long issueId;

    @Size(min = 3, max = 50, message = "El asunto de la incidencia debe de contener entre 3 y 50 caracteres")
    private String subject;

    @Size(min = 3, max = 255, message = "El mensaje de la incidencia debe de contener entre 3 y 255 caracteres")
    private String message;

    private Timestamp createdAt;

    private Boolean isSolved;

    private Long userId;
}
