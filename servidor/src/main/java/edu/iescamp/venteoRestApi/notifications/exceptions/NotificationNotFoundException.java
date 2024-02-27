package edu.iescamp.venteoRestApi.notifications.exceptions;

import edu.iescamp.venteoRestApi.shared.exceptions.DataNotFoundException;

public class NotificationNotFoundException extends DataNotFoundException {

    public NotificationNotFoundException(Long id) {
        super("La notificación con id " + id + " no existe");
    }
}
