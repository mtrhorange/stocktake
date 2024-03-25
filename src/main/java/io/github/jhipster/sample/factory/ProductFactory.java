package io.github.jhipster.sample.factory;

import io.github.jhipster.sample.domain.Product;
import io.github.jhipster.sample.request.productRequest;

public interface ProductFactory {
    Product createProduct(productRequest prodRequest);
}
