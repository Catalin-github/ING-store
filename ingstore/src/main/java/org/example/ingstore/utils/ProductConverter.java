package org.example.ingstore.utils;

import org.example.ingstore.dto.ProductDTO;
import org.example.ingstore.dto.ProductRequestDTO;
import org.example.ingstore.entity.Product;

public class ProductConverter {
    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());
        return dto;
    }

    public static Product fromRequestDTO(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        return product;
    }
}


