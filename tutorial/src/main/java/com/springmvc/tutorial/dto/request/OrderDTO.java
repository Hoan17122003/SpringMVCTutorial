package com.springmvc.tutorial.dto.request;

import com.springmvc.tutorial.model.entities.Customer;
import com.springmvc.tutorial.model.entities.Employee;
import com.springmvc.tutorial.model.entities.Product;

import java.io.Serializable;


public class OrderDTO implements Serializable {
    private final Long serializableOrderID = 1L;

    Integer productID;
    Integer employeeID;
    Integer customerID;
    int amount;
    double salePrice;
    double totalPrice;

}
