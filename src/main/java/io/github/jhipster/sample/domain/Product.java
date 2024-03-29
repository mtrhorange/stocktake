package io.github.jhipster.sample.domain;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    private String name;

    private String description;

    private int quantity;

    private long price;

    // Constructors, Getters, and Setters

    public Product() {
        // Default constructor
    }

    public Product(String name, String description, int quantity, long price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getProductID() {
        return product_id;
    }

    public void setProductID(Long productID) {
        this.product_id = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
