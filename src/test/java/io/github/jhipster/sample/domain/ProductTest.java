package io.github.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.jhipster.sample.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    void equalsVerifier() throws Exception {
        // given
        Product product1 = new Product("Phone", "Apple iPhone ProMax15", 10, 500);
        Product product2 = new Product("Phone", "Apple iPhone ProMax15", 10, 500);
        Product product3 = new Product("Bedsheet", "IKEA Sky Blue Single", 20, 100);
        // test for equality
        assertThat(product1).isNotEqualTo(product3); // different products

        assertThat(product1).isEqualTo(product2); // same products
        // test for hashcode consistency
        assertThat(product1.hashCode()).isEqualTo(product2.hashCode()); // same products
    }
}
