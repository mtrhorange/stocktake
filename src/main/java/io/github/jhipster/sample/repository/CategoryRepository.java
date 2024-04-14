package io.github.jhipster.sample.repository;

import io.github.jhipster.sample.domain.Category;
import io.github.jhipster.sample.domain.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Method to find a product by its name
    Category findByName(String name);

    boolean existsByName(String name);

    long deleteByName(String itemName);

    long deleteByCategoryId(Long id);

    void deleteByCategoryIdIn(Long[] id);
}
