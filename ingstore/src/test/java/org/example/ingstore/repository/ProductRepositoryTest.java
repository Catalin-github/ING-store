package org.example.ingstore.repository;

import org.example.ingstore.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("Apple Watch");
        product.setCategory("Electronics");
        product.setPrice(100.0);

        Product savedProduct = productRepository.save(product);
        assertNotNull(savedProduct.getId());
    }


    @Test
    @DisplayName("Test find product by id")
    public void testFindById() {
        Product product = new Product();
        product.setName("Apple Watch");
        product.setCategory("Electronics");
        product.setPrice(99.99);
        productRepository.save(product);

        Optional<Product> foundProduct = productRepository.findById(product.getId());

        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo("Apple Watch");
    }

    @Test
    @DisplayName("Test delete product")
    public void testDeleteProduct() {
        Product product = new Product();
        product.setName("Apple Watch");
        product.setCategory("Electronics");
        product.setPrice(99.99);
        productRepository.save(product);

        productRepository.delete(product);

        Optional<Product> deletedProduct = productRepository.findById(product.getId());

        assertThat(deletedProduct).isEmpty();
    }
}