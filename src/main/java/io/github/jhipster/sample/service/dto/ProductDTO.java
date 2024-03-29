package io.github.jhipster.sample.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.sample.domain.Product} entity.
 */
@Data
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductDTO implements Serializable {

    private Long product_id;

    private String name;

    private String description;

    private int quantity;

    private long price;
}
