package org.example.ingstore.controller;


import jakarta.validation.Valid;
import org.example.ingstore.dto.ApiResponseDTO;
import org.example.ingstore.dto.ProductDTO;
import org.example.ingstore.dto.ProductRequestDTO;
import org.example.ingstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.ingstore.utils.AppConstants.*;

@RestController
@RequestMapping(PRODUCT_BASE_PATH)
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<ProductDTO>>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        ApiResponseDTO<List<ProductDTO>> response = new ApiResponseDTO<>(products, PRODUCTS_FETCH_SUCCESS);
        return new ResponseEntity<>(response, HttpStatus.valueOf(HTTP_STATUS_OK));
    }

    @GetMapping(GET_BY_ID_PATH)
    public ResponseEntity<ApiResponseDTO<ProductDTO>> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.findProductById(id);
        ApiResponseDTO<ProductDTO> response = new ApiResponseDTO<>(product, PRODUCT_FETCH_SUCCESS);
        return new ResponseEntity<>(response, HttpStatus.valueOf(HTTP_STATUS_OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<ProductDTO>> createProduct(@Valid @RequestBody ProductRequestDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        ApiResponseDTO<ProductDTO> response = new ApiResponseDTO<>(createdProduct, PRODUCT_CREATE_SUCCESS);
        return new ResponseEntity<>(response, HttpStatus.valueOf(HTTP_STATUS_CREATED));
    }

    @PutMapping(GET_BY_ID_PATH)
    public ResponseEntity<ApiResponseDTO<ProductDTO>> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        ApiResponseDTO<ProductDTO> response = new ApiResponseDTO<>(updatedProduct, PRODUCT_UPDATE_SUCCESS);
        return new ResponseEntity<>(response, HttpStatus.valueOf(HTTP_STATUS_OK));
    }

    @DeleteMapping(GET_BY_ID_PATH)
    public ResponseEntity<ApiResponseDTO<Void>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        ApiResponseDTO<Void> response = new ApiResponseDTO<>(null, PRODUCT_DELETE_SUCCESS);
        return new ResponseEntity<>(response, HttpStatus.valueOf(HTTP_STATUS_NO_CONTENT));
    }
}
