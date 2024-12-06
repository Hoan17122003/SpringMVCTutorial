package com.springmvc.tutorial.model.repository.order;

import com.springmvc.tutorial.dto.request.OrderSearchDTO;
import com.springmvc.tutorial.model.entities.Order;
import com.springmvc.tutorial.model.entities.OrderDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Status;
import jakarta.transaction.Transactional;

import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// @Repository
@Transactional
public class OrderRepositoryImpl implements IOrderRepositoryCustom {

    // @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public OrderRepositoryImpl(EntityManager entityManager) {
        // this.entityManager = context.getEntityManagerByManagedType(Order.class);
        this.entityManager = entityManager;
    }

    // @Override
    // public Order insertOrder(Order entity) {
    //// var result = entityManager.persist(entity);
    // return null;
    // }
    //
    // @Override
    // public Order updateOrder(Order entity, int orderId, int status) {
    //// Order existingOrder = entityManager.find(Order.class, orderId);
    ////// var statusCheck = entityManager.createQuery("select * from status where
    // status = :status").setParameter("status",
    // status).getResultStream().findFirst().stream().findFirst().orElse(null);
    //// if (existingOrder != null) {
    //// existingOrder.setStatus(status);
    //// entityManager.merge(existingOrder);
    //// }
    //// return existingOrder;
    // return null;
    // }

    @Override
    public List<Order> getAllOrderByEmployeeID(int employeeID) {
        return entityManager.createQuery("SELECT o FROM Order o WHERE o.EmployeeID = :employeeID", Order.class)
                .setParameter("employeeID", employeeID)
                .getResultStream()
                .findFirst().stream().toList();
    }

    // @SuppressWarnings("unchecked")
    // @Override
    // public List<OrderSearchDTO> searchOrders(int pageNumber, int pageSize, int
    // status, LocalDate dateStart,
    // LocalDate dateEnd,
    // String searchValue) {
    // StoredProcedureQuery query =
    // entityManager.createStoredProcedureQuery("SearchOrders")
    // .setParameter("PageNumber", pageNumber).setParameter("PageSize", pageSize)
    // .setParameter("DateStart", dateStart).setParameter("DateEnd", dateEnd)
    // .setParameter("SearchValue", searchValue)
    // .setParameter("Status", status);

    // List<Object[]> results = query.getResultList();
    // List<OrderSearchDTO> orders = new ArrayList<>();
    // for (Object[] result : results) {
    // OrderSearchDTO order = new OrderSearchDTO();
    // order.setOrderID((Integer) result[0]);
    // order.setCustomerName((String) result[1]);
    // order.setOrderTime((LocalDate) result[2]);
    // order.setEmployeeName((String) result[3]);
    // order.setAcceptTime((LocalDate) result[4]);
    // order.setShipperName((String) result[5]);
    // order.setShippedTime((LocalDate) result[6]);
    // order.setFinishedTime((LocalDate) result[7]);
    // order.setStatusDescription((String) result[8]);
    // orders.add(order);
    // }
    // return orders;
    // }
}
