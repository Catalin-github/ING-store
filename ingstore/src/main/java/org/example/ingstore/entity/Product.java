package org.example.ingstore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String name;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive value")
    @Column(nullable = false)
    @ToString.Include
    private Double price;

    @NotNull(message = "Category cannot be null")
    @Column(nullable = false)
    private String category;
}