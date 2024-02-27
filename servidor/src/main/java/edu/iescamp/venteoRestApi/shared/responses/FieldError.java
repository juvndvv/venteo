package edu.iescamp.venteoRestApi.shared.responses;

import lombok.Data;
import lombok.Getter;

@Data
public class FieldError {
    private final String field;
    private final String message;

    public FieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
