package edu.iescamp.venteoRestApi.users.exceptions;

import edu.iescamp.venteoRestApi.shared.exceptions.DataNotFoundException;

public class UserNotFoundException extends DataNotFoundException {
    public UserNotFoundException(Long id) {
        super("El usuario con id " + id + " no existe");
    }
}
