package edu.iescamp.venteoRestApi.users.models.dto;

import lombok.Data;

@Data
public class LoginFormDTO {
    private String username;
    private String password;
}
