package io.github.jhipster.sample.web.rest;

import io.github.jhipster.sample.domain.Category;
import io.github.jhipster.sample.service.CategoryService;
import io.github.jhipster.sample.service.dto.CategoryDTO;
import io.github.jhipster.sample.service.dto.CategoryProductDTO;
import jakarta.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAllCategories")
    public List<CategoryProductDTO> getAllCategories() {
        return categoryService.getAllCategoriesWithCount();
    }

    @GetMapping("/getCategory/{categoryId}")
    public CategoryDTO findCategory(@PathVariable(value = "categoryId") Long categoryId) {
        return categoryService.findById(categoryId).orElseThrow();
    }

    @PostMapping("/createCategory")
    public Category createCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws SQLIntegrityConstraintViolationException {
        return categoryService.addCategory(categoryDTO);
    }

    @PostMapping("/deleteCategories")
    public String deleteCategory(@Valid @RequestBody Long[] id) {
        return categoryService.deleteCategories(id);
    }

    @PutMapping("/updateCategory/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO updatedCategory) {
        try {
            categoryService.updateCategory(categoryId, updatedCategory);
            return ResponseEntity.ok("Category updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update category failed.");
        }
    }
}
