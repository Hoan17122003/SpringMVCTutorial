package com.springmvc.tutorial.service;

import com.springmvc.tutorial.dto.request.OrderSearchDTO;
import com.springmvc.tutorial.model.entities.Order;

import java.util.List;

public interface IOrderService {

    public void createOrder(Order entity);

    public void updateOrder(Order entity, int orderId);

    List<Order> getAllOrderByEmployeeId(int employeeId);

    List<OrderSearchDTO> searchOrder(String searchValue, int status, String dateRange, int page, int PAGE_SIZE);
}