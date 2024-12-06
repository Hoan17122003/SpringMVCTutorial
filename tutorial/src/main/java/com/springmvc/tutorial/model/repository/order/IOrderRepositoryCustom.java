package com.springmvc.tutorial.model.repository.order;

import com.springmvc.tutorial.dto.request.OrderSearchDTO;
import com.springmvc.tutorial.model.entities.Order;
import com.springmvc.tutorial.model.entities.OrderDetail;

import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IOrderRepositoryCustom {

    // Order insertOrder(Order entity);
    //
    // Order updateOrder(Order entity, int orderId, int status);

    List<Order> getAllOrderByEmployeeID(int employeeID);

    // List<OrderSearchDTO> searchOrders(int pageNumber, int pageSize, int status,
    // LocalDate dateStart, LocalDate dateEnd,
    // String searchValue);
}
