package edu.iescamp.venteoRestApi.users.web.controllers;

import edu.iescamp.venteoRestApi.users.models.dto.LoginFormDTO;
import edu.iescamp.venteoRestApi.users.models.dto.UserDTO;
import edu.iescamp.venteoRestApi.users.repositories.entities.User;
import edu.iescamp.venteoRestApi.users.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<UserDTO> authenticate(@RequestBody LoginFormDTO loginFormDTO) {
        UserDTO authenticated = authenticationService.authenticate(loginFormDTO);

        System.out.println(authenticated.getMoney());

        if (authenticated != null) {
            return ResponseEntity.status(HttpStatus.OK).body(authenticated);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticated);
        }
    }
}
