package io.github.jhipster.sample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jhipster.sample.domain.Product;
import io.github.jhipster.sample.repository.ProductRepository;
import io.github.jhipster.sample.request.productRequest;
import io.github.jhipster.sample.service.dto.ProductDTO;
import jakarta.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {

    private Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    // Method to add a product
    public Product addProduct(ProductDTO productRequest) throws SQLIntegrityConstraintViolationException {
        try {
            if (!productRepository.existsByName(productRequest.getName())) {
                ObjectMapper objectMapper = new ObjectMapper();
                //Product newProduct = productFactory.createProduct(productRequest);
                Product newProduct = objectMapper.convertValue(productRequest, Product.class);
                return productRepository.save(newProduct);
            } else {
                throw new DataIntegrityViolationException("Product already exists");
            }
        } catch (DataIntegrityViolationException e) {
            throw new SQLIntegrityConstraintViolationException("Product already exists");
        }
    }

    // Method to delete a product by ID
    public String deleteProduct(Long id) {
        long productDeleted = productRepository.deleteByProductId(id);
        if (productDeleted == 0) {
            throw new NotFoundException("Product id not found");
        } else {
            return "Product: " + id + " deleted";
        }
    }

    public String deleteProducts(Long[] id) {
        productRepository.deleteByProductIdIn(id);
        //        if (productDeleted == 0) {
        //            throw new NotFoundException("Product id not found");
        //        } else {
        //            return "Products deleted: " + id;
        //        }
        return "Products deleted: " + id.toString();
    }

    // Method to update a product
    public void updateProduct(Long productId, Product updatedProduct) {
        // Check if the item with the provided ID exists in the database
        Product existingProduct = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        System.out.println(existingProduct.toString());

        // Update properties of existingProduct with values from updatedProduct
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setCategory(updatedProduct.getCategory());

        // Save the updated existingProduct to the database
        productRepository.save(existingProduct);
    }

    // Method to retrieve a product by ID
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    // Method to retrieve all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Method to find products by name
    public Product findProductsByName(String name) {
        Product product = productRepository.findByName(name);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        return product;
    }

    public Product findProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new NotFoundException("Product not found");
        }
        return product.orElseThrow(() -> new NotFoundException("Product not found"));
    }

    // Method to find product names containing the input parameter
    public List<String> findProductNamesContaining(String keyword) {
        List<Product> productsContainingKeyword = productRepository.findByNameContaining(keyword);
        return productsContainingKeyword.stream().map(Product::getName).collect(Collectors.toList());
    }
}
