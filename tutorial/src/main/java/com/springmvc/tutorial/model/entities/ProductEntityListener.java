package com.springmvc.tutorial.model.entities;

import com.springmvc.tutorial.helper.ApplicationContextProvider;

import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;

public class ProductEntityListener {
    @PostUpdate
    public void onUpdate(Product product) {
        System.out.println("Product updated: " + product.getProductName());
        // Gửi sự kiện khi cập nhật sản phẩm
        ApplicationContextProvider.publishEvent(new ProductChangeEvent(product, "UPDATE"));
    }

    @PostRemove
    public void onDelete(Product product) {
        System.out.println("Product deleted: " + product.getProductName());
        // Gửi sự kiện khi xóa sản phẩm
        ApplicationContextProvider.publishEvent(new ProductChangeEvent(product, "DELETE"));
    }
}
