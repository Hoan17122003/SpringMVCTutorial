package com.springmvc.tutorial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.tutorial.dto.request.OrderSearchDTO;
import com.springmvc.tutorial.model.entities.Order;
import com.springmvc.tutorial.model.repository.order.IOrderRepository;
import com.springmvc.tutorial.service.IOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    // Other methods for order management
    @Override
    public void createOrder(Order entity) {

    }

    public void updateOrder(Order entity, int orderId) {
        // this.orderRepository
    }

    public List<Order> getAllOrderByEmployeeId(int employeeId) {
        return this.orderRepository.getAllOrderByEmployeeID(employeeId).stream().toList();
    }

    public List<OrderSearchDTO> searchOrder(String searchValue, int status, String dateRange, int page, int PAGE_SIZE) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        String[] dateString = dateRange.split("-");
        LocalDate dateStart = LocalDate.parse(dateString[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dateEnd = LocalDate.parse(dateString[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        var result = this.orderRepository.searchOrders(searchValue, status, dateStart, dateEnd,
                pageable).getContent();

        return result;
    }
}
