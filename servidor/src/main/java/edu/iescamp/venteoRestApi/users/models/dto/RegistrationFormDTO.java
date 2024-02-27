package edu.iescamp.venteoRestApi.users.models.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationFormDTO {
    @NotNull(message = "El nombre de usuario no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
    private String userName;

    @NotNull(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String firstName;

    @NotNull(message = "El apellido no puede estar vacío")
    @Size(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caracteres")
    private String lastName;

    @NotNull(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotNull(message = "La contraseña no puede estar vacía")
    private String password;

    @NotNull(message = "La contraseña no puede estar vacía")
    private String repeatPassword;

    private String imageUrl;

    @AssertTrue(message = "Las contraseñas deben coincidir")
    private boolean isValid() {
        if (password == null || repeatPassword == null) {
            return false;
        }
        return password.equals(repeatPassword);
    }
}
