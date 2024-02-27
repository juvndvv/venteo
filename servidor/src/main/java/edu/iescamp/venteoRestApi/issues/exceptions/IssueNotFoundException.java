package edu.iescamp.venteoRestApi.issues.exceptions;

import edu.iescamp.venteoRestApi.shared.exceptions.DataNotFoundException;

public class IssueNotFoundException extends DataNotFoundException {

    public IssueNotFoundException(Long id) {
        super("La imcidencia con id " + id + " no existe");
    }

}
