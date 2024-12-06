package com.springmvc.tutorial.model.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListener {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @EventListener
    public void handleProductChange(ProductChangeEvent event) {
        Product product = event.getProduct();
        String action = event.getAction();

        switch (action) {
            case "DELETE":
                // Xóa sản phẩm khỏi Redis
                redisTemplate.delete("product:" + product.getProductID());
                System.out.println("Removed product from Redis: " + product.getProductName());
                break;

            case "UPDATE":
                // Cập nhật sản phẩm trong Redis
                redisTemplate.opsForValue().set("product:" + product.getProductName(), product);
                System.out.println("Updated product in Redis: " + product.getProductName());
                break;
        }
    }
}
