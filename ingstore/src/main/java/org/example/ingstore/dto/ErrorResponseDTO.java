package org.example.ingstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ErrorResponseDTO {

    private int statusCode;
    private String message;
    private Instant timestamp;
    private Map<String, String> validationErrors;

    // Constructor for general errors
    public ErrorResponseDTO(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = Instant.now();
    }

    // Constructor for validation errors
    public ErrorResponseDTO(int statusCode, String message, Map<String, String> validationErrors) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = Instant.now();
        this.validationErrors = validationErrors;
    }
}
