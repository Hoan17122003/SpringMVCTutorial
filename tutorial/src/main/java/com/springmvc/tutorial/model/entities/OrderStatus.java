package com.springmvc.tutorial.model.entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;
import java.util.Optional;

@Entity
@Table(
        name = "OrderStatus"
)
public class OrderStatus {
    @Id
    private int Status;

    @Column(
            name = "Description"
    )
    private String description;

    public OrderStatus() {
    }

    public OrderStatus(int status, String description) {
        Status = status;
        this.description = description;
    }

    public int getStatus() {
        return Status;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    //realtions
    @OneToMany(mappedBy = "orderStatusRelations", cascade = CascadeType.ALL)
    private List<Order> orders;
}
