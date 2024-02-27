package edu.iescamp.venteoRestApi.users.services;

import edu.iescamp.venteoRestApi.users.models.dto.LoginFormDTO;
import edu.iescamp.venteoRestApi.users.models.dto.UserDTO;
import edu.iescamp.venteoRestApi.users.repositories.entities.User;
import org.apache.catalina.UserDatabase;

public interface AuthenticationService {
    public UserDTO authenticate(LoginFormDTO loginFormDTO);
}
