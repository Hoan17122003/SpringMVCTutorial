package com.springmvc.tutorial.model.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.*;

@Embeddable
public class OrderDetailKey implements Serializable {
    private int orderID;
    private int productID;

    public OrderDetailKey(int orderID, int productID) {
        this.orderID = orderID;
        this.productID = productID;
    }
    public OrderDetailKey() {
    }


    public int getOrderID() {
        return orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailKey that = (OrderDetailKey) o;
        return Objects.equals(orderID, that.orderID) && Objects.equals(productID, that.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, productID);
    }
}
