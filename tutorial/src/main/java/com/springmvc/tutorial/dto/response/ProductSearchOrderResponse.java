package com.springmvc.tutorial.dto.response;

import java.util.List;

import com.springmvc.tutorial.model.entities.Order;

public class ProductSearchOrderResponse {
    List<Order> orders;
    int RowCount;
    int Pagecount;

}
