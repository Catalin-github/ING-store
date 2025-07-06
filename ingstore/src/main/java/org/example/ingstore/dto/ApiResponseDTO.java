package org.example.ingstore.dto;

import lombok.*;
import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApiResponseDTO<T> {
    private T data;
    private String message;
    private Instant timestamp;

    public ApiResponseDTO(T data, String message) {
        this.data = data;
        this.message = message;
        this.timestamp = Instant.now();
    }
}