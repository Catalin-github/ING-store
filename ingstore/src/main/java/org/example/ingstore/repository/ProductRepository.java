package org.example.ingstore.repository;

 import org.example.ingstore.entity.Product;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find a product by its name
    Optional<Product> findByName(String name);

    // Find all products by category (if your Product entity has a category field)
    List<Product> findByCategory(String category);  // This will now work if the category field exists in Product
}