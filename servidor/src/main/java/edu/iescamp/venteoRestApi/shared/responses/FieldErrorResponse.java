package edu.iescamp.venteoRestApi.shared.responses;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

@Getter
public class FieldErrorResponse {
    private final List<FieldError> errors;
    public FieldErrorResponse(BindingResult bindingResult) {
        this.errors = Arrays.asList(bindingResult.getFieldErrors().stream().map(objectError -> {
            return new FieldError(
            objectError.getField(),
            objectError.getDefaultMessage()
            );
        }).toArray(FieldError[]::new));

    }
}
