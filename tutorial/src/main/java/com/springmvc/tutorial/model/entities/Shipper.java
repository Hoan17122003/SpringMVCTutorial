package com.springmvc.tutorial.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Shippers")
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "ShipperID"
    )
    private int shipperID;


    @Column(
            name = "ShipperName",
            columnDefinition = "nvarchar(30)",
            nullable = false
    )
    private String shipperName;

    @Column(
            name = "Phone",
            nullable = false,
            columnDefinition = "nvarchar(20)",
            unique = true
    )
    private String phone;

    public Shipper() {
    }

    public Shipper(String shipperName, String phone) {
        this.shipperName = shipperName;
        this.phone = phone;
    }

    public int getShipperID() {
        return shipperID;
    }

    public String getShipperName() {
        return shipperName;
    }

    public String getPhone() {
        return phone;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    //relations

    @OneToMany(
            mappedBy = "shipperRelations",
            cascade = CascadeType.ALL
    )
    private List<Order> orders;

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
