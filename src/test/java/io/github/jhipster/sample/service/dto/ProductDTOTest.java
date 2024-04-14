package io.github.jhipster.sample.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.jhipster.sample.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ProductDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        // given
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductID(1L);
        productDTO.setName("Phone");
        productDTO.setDescription("Apple iPhone ProMax15");
        productDTO.setQuantity(10);
        productDTO.setPrice(500);
        //        productDTO.setCategory("Electronics");

        // retrieving values that were initialised during productDTO instantiation
        long productId = productDTO.getProductID();
        String name = productDTO.getName();
        String description = productDTO.getDescription();
        int quantity = productDTO.getQuantity();
        long price = productDTO.getPrice();
        //        CategoryDTO category = productDTO.getCategory();

        // test for equality
        assertThat(productId).isEqualTo(1L);
        assertThat(name).isEqualTo("Phone");
        assertThat(description).isEqualTo("Apple iPhone ProMax15");
        assertThat(quantity).isEqualTo(10);
        assertThat(price).isEqualTo(500);
        //        assertThat(category).isEqualTo("Electronics");

    }
}
