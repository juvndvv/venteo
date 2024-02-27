package edu.iescamp.venteoRestApi.users.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import java.util.Date;

@Builder
@Data
public class UserDTO {
    private Long id;

    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
    @NotNull(message = "El nombre de usuario no puede ser nulo")
    private String firstName;

    @Size(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caracteres")
    @NotNull(message = "El apellido no puede ser nulo")
    private String lastName;

    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
    @NotNull(message = "El nombre de usuario no puede ser nulo")
    private String userName;

    @Size(min = 3, max = 50, message = "La contraseña debe tener entre 3 y 50 caracteres")
    private String password;

    @Email(message = "El email debe ser válido")
    @NotNull(message = "El email no puede ser nulo")
    private String email;

    private Date birthDate;

    private String imageUrl;

    private Long roleId;

    private double money;
}
