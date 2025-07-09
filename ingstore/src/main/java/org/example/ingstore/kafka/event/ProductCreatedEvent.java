package org.example.ingstore.kafka.event;

import lombok.*;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreatedEvent implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String category;
    private LocalDateTime createdAt;
}