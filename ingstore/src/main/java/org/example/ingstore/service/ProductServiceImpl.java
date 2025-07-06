package org.example.ingstore.service;

import org.example.ingstore.dto.ProductDTO;
import org.example.ingstore.dto.ProductRequestDTO;
import org.example.ingstore.entity.Product;
import org.example.ingstore.exception.ResourceNotFoundException;
import org.example.ingstore.repository.ProductRepository;
import org.example.ingstore.utils.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.ingstore.utils.AppConstants.PRODUCT_NOT_FOUND_WITH_ID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO createProduct(ProductRequestDTO productRequest) {
        Product product = ProductConverter.fromRequestDTO(productRequest);
        Product savedProduct = productRepository.save(product);
        return ProductConverter.toDTO(savedProduct);
    }

    @Override
    public ProductDTO findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND_WITH_ID + id));
        return ProductConverter.toDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductRequestDTO productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND_WITH_ID + id));
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        Product updatedProduct = productRepository.save(product);
        return ProductConverter.toDTO(updatedProduct);
    }
}
