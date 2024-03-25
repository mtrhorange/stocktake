package io.github.jhipster.sample.repository;

import io.github.jhipster.sample.domain.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Method to find a product by its name
    Product findByName(String name);

    // Method to find products whose names contain the given keyword
    List<Product> findByNameContaining(String keyword);

    boolean existsByName(String name);

    long deleteByName(String itemName);
}
