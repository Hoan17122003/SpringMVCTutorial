package com.springmvc.tutorial.model.entities;

import jakarta.persistence.*;

@Entity
@Table
public class OrderDetail {

    @EmbeddedId
    private OrderDetailKey id;

    // Định nghĩa các quan hệ khóa ngoại
    @ManyToOne
    @MapsId("orderID")  // Maps to the orderId field in OrderProductKey
    @JoinColumn(name = "orderID")
    private Order order;

    @ManyToOne
    @MapsId("productID")  // Maps to the productId field in OrderProductKey
    @JoinColumn(name = "productID")
    private Product product;

    // Các thuộc tính bổ sung
    @Column(name = "Quanlity", columnDefinition = "int", nullable = false)
    private Integer quantity;
    @Column(name = "SalePrice", nullable = false, columnDefinition = "Decimal(15,2)")
    private Double salePrice;

    public OrderDetail() {
    }

    public OrderDetail(Order order, Product product, Integer quantity, Double salePrice) {
        this.id = new OrderDetailKey(order.getOrderID(), product.getProductID());
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.salePrice = salePrice;
    }
}
