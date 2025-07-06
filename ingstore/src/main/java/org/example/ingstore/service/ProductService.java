package org.example.ingstore.service;

import org.example.ingstore.dto.ProductDTO;
import org.example.ingstore.dto.ProductRequestDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductRequestDTO productRequest);

    ProductDTO findProductById(Long id);

    List<ProductDTO> getAllProducts();

    void deleteProduct(Long id);

    ProductDTO updateProduct(Long id, ProductRequestDTO productRequest);
}