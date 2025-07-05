package org.example.ingstore.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRequestDTO {
    private String name;
    private double price;
    private String category;
}

