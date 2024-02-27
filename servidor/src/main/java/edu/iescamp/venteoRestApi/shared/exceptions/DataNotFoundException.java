package edu.iescamp.venteoRestApi.shared.exceptions;

public class DataNotFoundException extends RuntimeException {
        public DataNotFoundException(String message) {
            super(message);
        }
}
