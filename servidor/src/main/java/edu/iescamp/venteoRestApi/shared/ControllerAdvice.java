package edu.iescamp.venteoRestApi.shared;

import edu.iescamp.venteoRestApi.shared.exceptions.DataNotFoundException;
import edu.iescamp.venteoRestApi.shared.responses.FieldErrorResponse;
import edu.iescamp.venteoRestApi.shared.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> auctionNotFoundException(DataNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FieldErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldErrorResponse fieldErrorResponse = new FieldErrorResponse(ex.getBindingResult());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldErrorResponse);
    }
}