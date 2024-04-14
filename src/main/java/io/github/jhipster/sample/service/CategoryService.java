package io.github.jhipster.sample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jhipster.sample.domain.Category;
import io.github.jhipster.sample.domain.Product;
import io.github.jhipster.sample.repository.CategoryRepository;
import io.github.jhipster.sample.repository.ProductRepository;
import io.github.jhipster.sample.request.productRequest;
import io.github.jhipster.sample.service.dto.CategoryDTO;
import io.github.jhipster.sample.service.dto.CategoryProductDTO;
import jakarta.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryService {

    private Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public CategoryService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // Method to add a product
    public Category addCategory(CategoryDTO categoryDTO) throws SQLIntegrityConstraintViolationException {
        try {
            log.debug("Request add category: {}", categoryDTO);
            if (!categoryRepository.existsByName(categoryDTO.getName())) {
                log.debug("Request add category 2: {}", categoryDTO);
                //Product newProduct = productFactory.createProduct(productRequest);
                Category newCategory = objectMapper.convertValue(categoryDTO, Category.class);
                log.debug("Request add category 3: {}", newCategory);
                return categoryRepository.save(newCategory);
            } else {
                throw new DataIntegrityViolationException("Category already exists");
            }
        } catch (DataIntegrityViolationException e) {
            throw new SQLIntegrityConstraintViolationException("Category already exists");
        }
    }

    public String deleteCategories(Long[] id) {
        categoryRepository.deleteByCategoryIdIn(id);
        //        if (productDeleted == 0) {
        //            throw new NotFoundException("Product id not found");
        //        } else {
        //            return "Products deleted: " + id;
        //        }
        return "Categories deleted: " + id.toString();
    }

    // Method to update a product
    public void updateCategory(Long categoryId, CategoryDTO updatedCategory) {
        // Check if the item with the provided ID exists in the database
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found"));
        System.out.println(existingCategory.toString());

        // Update properties of existingProduct with values from updatedProduct
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription());

        // Save the updated existingProduct to the database
        categoryRepository.save(existingCategory);
    }

    // Method to retrieve a product by ID
    public Optional<CategoryDTO> findById(Long categoryId) {
        return Optional.of(objectMapper.convertValue(categoryRepository.findById(categoryId), CategoryDTO.class));
    }

    // Method to retrieve all products
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<CategoryProductDTO> getAllCategoriesWithCount() {
        List<Object[]> categoryResults = categoryRepository.findAllWithCount();

        List<CategoryProductDTO> categoryProductDTOList = categoryResults
            .stream()
            .map(catArr -> {
                CategoryProductDTO categoryProductDTO = new CategoryProductDTO();
                categoryProductDTO.setCategoryId(toSafeLong(catArr[0]));
                categoryProductDTO.setName(toSafeString(catArr[1]));
                categoryProductDTO.setDescription(toSafeString(catArr[2]));
                categoryProductDTO.setProductCount(toSafeLong(catArr[3]));

                return categoryProductDTO;
            })
            .toList();

        return categoryProductDTOList;
    }

    private Long toSafeLong(Object value) {
        if (Objects.nonNull(value)) {
            String strValue = String.valueOf(value);
            if (Objects.nonNull(strValue) && StringUtils.isNumeric(strValue.trim())) {
                return Long.valueOf(strValue);
            }
        }
        return null;
    }

    private String toSafeString(Object value) {
        if (Objects.nonNull(value)) {
            String strValue = String.valueOf(value);
            if (StringUtils.isNotBlank(strValue)) {
                return strValue.trim();
            }
        }
        return null;
    }
}
