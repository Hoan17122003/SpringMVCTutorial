package com.springmvc.tutorial.model.entities;

public class ProductChangeEvent {
    private final Product product;
    private final String action; // "DELETE" hoặc "UPDATE"

    public ProductChangeEvent(Product product, String action) {
        this.product = product;
        this.action = action;
    }

    public Product getProduct() {
        return product;
    }

    public String getAction() {
        return action;
    }
}
