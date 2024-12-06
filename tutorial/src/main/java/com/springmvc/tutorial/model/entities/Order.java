package com.springmvc.tutorial.model.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID", columnDefinition = "int")
    private int orderID;

    @Column(name = "CustomerID", columnDefinition = "int", nullable = false)
    private int customerID;

    @Column(name = "OrderTime", nullable = false, columnDefinition = "datetime")
    private LocalDateTime orderTime = LocalDateTime.now();

    @Column(name = "DeliveryProvince", columnDefinition = "nvarchar(255)", nullable = false)
    private String deliveryProvince;

    @Column(name = "deliveryAddress", columnDefinition = "nvarchar(255)", nullable = false)
    private String deliveryAddress;

    @Column(name = "EmployeeID", columnDefinition = "int", nullable = false)
    private Integer employeeID = null;

    @Column(name = "AcceptTime", columnDefinition = "datetime")
    private LocalDateTime acceptTime = null;

    @Column(name = "ShipperID", columnDefinition = "int default null")
    private Integer shipperID;

    @Column(name = "ShippedTime", columnDefinition = "datetime")
    private LocalDateTime ShippedTime = null;

    @Column(columnDefinition = "datetime", name = "FinishedTime")
    private LocalDateTime finishedTime;

    @Column(columnDefinition = "int default 0", nullable = false)
    private int status;

    public Order() {
    }

    public Order(int customerID, LocalDateTime orderTime, String deliveryProvince, String deliveryAddress,
            Integer employeeID, LocalDateTime acceptTime, Integer shipperID, LocalDateTime shippedTime) {
        this.customerID = customerID;
        this.orderTime = orderTime;
        this.deliveryProvince = deliveryProvince;
        this.deliveryAddress = deliveryAddress;
        this.employeeID = employeeID;
        this.acceptTime = acceptTime;
        this.shipperID = shipperID;
        ShippedTime = shippedTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setFinishedTime(LocalDateTime finishedTime) {
        this.finishedTime = finishedTime;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public void setDeliveryProvince(String deliveryProvince) {
        this.deliveryProvince = deliveryProvince;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public void setAcceptTime(LocalDateTime acceptTime) {
        this.acceptTime = acceptTime;
    }

    public void setShipperID(Integer shipperID) {
        this.shipperID = shipperID;
    }

    public void setShippedTime(LocalDateTime shippedTime) {
        ShippedTime = shippedTime;
    }

    public void setCustomerRelations(Customer customerRelations) {
        this.customerRelations = customerRelations;
    }

    public void setEmployeeRelations(Employee employeeRelations) {
        this.employeeRelations = employeeRelations;
    }

    public void setOrderStatusRelations(OrderStatus orderStatusRelations) {
        this.orderStatusRelations = orderStatusRelations;
    }

    public void setShipperRelations(Shipper shipperRelations) {
        this.shipperRelations = shipperRelations;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public String getDeliveryProvince() {
        return deliveryProvince;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public LocalDateTime getAcceptTime() {
        return acceptTime;
    }

    public Integer getShipperID() {
        return shipperID;
    }

    public LocalDateTime getShippedTime() {
        return ShippedTime;
    }

    public LocalDateTime getFinishedTime() {
        return finishedTime;
    }

    public Customer getCustomerRelations() {
        return customerRelations;
    }

    public Employee getEmployeeRelations() {
        return employeeRelations;
    }

    public OrderStatus getOrderStatusRelations() {
        return orderStatusRelations;
    }

    public Shipper getShipperRelations() {
        return shipperRelations;
    }

    // relations

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, name = "CustomerID")
    private Customer customerRelations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, name = "EmployeeID")
    private Employee employeeRelations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, name = "Status")
    private OrderStatus orderStatusRelations;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(insertable = false, updatable = false, name = "ShipperID")
    private Shipper shipperRelations;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;
}
