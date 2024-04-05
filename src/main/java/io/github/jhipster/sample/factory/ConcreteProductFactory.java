package io.github.jhipster.sample.factory;

import io.github.jhipster.sample.domain.Product;
import io.github.jhipster.sample.request.productRequest;
import org.springframework.stereotype.Component;

@Component
public class ConcreteProductFactory implements ProductFactory {

    public Product createProduct(productRequest prodRequest) {
        Product product = new Product(
            prodRequest.getName(),
            prodRequest.getDescription(),
            prodRequest.getQuantity(),
            prodRequest.getPrice()
        );

        // product.setName(prodRequest.getName());
        // product.setDescription(prodRequest.getDescription());
        // product.setQuantity(prodRequest.getQuantity());
        // product.setPrice(prodRequest.getPrice());

        return product;
    }
}
