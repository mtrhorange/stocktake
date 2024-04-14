package io.github.jhipster.sample.web.rest;

import io.github.jhipster.sample.domain.Product;
import io.github.jhipster.sample.request.productRequest;
import io.github.jhipster.sample.service.ProductService;
import io.github.jhipster.sample.service.dto.ProductDTO;
import jakarta.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getProductByName/{productName}")
    public Product findProductByName(@PathVariable(value = "productName") String productName) {
        return productService.findProductsByName(productName);
    }

    @GetMapping("/getProduct/{productId}")
    public Product findProduct(@PathVariable(value = "productId") Long productId) {
        return productService.findProduct(productId);
    }

    @PostMapping("/createProduct")
    public Product createProduct(@Valid @RequestBody ProductDTO productDTO) throws SQLIntegrityConstraintViolationException {
        return productService.addProduct(productDTO);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        return productService.deleteProduct(id);
    }

    @PostMapping("/deleteProducts")
    public String deleteProduct(@Valid @RequestBody Long[] id) {
        return productService.deleteProducts(id);
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        try {
            productService.updateProduct(productId, updatedProduct);
            return ResponseEntity.ok("Product updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update product failed.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.getProductById(id);
        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/searchContaining")
    public ResponseEntity<List<String>> findProductNamesContaining(@RequestParam String keyword) {
        List<String> productNames = productService.findProductNamesContaining(keyword);
        return ResponseEntity.ok(productNames);
    }
}
