package com.springmvc.tutorial.model.repository.order;

import com.springmvc.tutorial.dto.request.OrderSearchDTO;
import com.springmvc.tutorial.model.entities.Order;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer>, IOrderRepositoryCustom {

        @Modifying
        @Transactional
        @Query(nativeQuery = true, value = "insert into orderstatus (status,description) values(:status,:description)")
        void saveOrderStatus(@Param("status") Integer status, @Param("description") String description);

        @Query(nativeQuery = true, value = """
                        SELECT new com.springmvc.tutorial.dto.request.OrderSearchDTO
                                (o.OrderID, c.CustomerName, o.OrderTime, e.FullName, o.AcceptTime,
                                s.ShipperName, o.ShippedTime, o.FinishedTime, os.Description)
                        FROM orders o
                                JOIN customers c ON o.CustomerID = c.CustomerID
                                LEFT JOIN employees e ON o.EmployeeID = e.EmployeeID
                                LEFT JOIN shippers s ON o.ShipperID = s.ShipperID
                                JOIN orderstatus os ON o.Status = os.Status
                        WHERE (:searchValue IS NULL OR
                                LOWER(c.CustomerName) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR
                                LOWER(e.FullName ) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR
                                LOWER(s.ShipperName) LIKE LOWER(CONCAT('%', :searchValue, '%')))
                                AND (:status IS NULL OR o.Status = :status)
                                AND (:startDate IS NULL OR o.OrderTime >= :startDate)
                                AND (:endDate IS NULL OR o.OrderTime <= :endDate)
                        """)
        Page<OrderSearchDTO> searchOrders(
                        @Param("searchValue") String searchValue,
                        @Param("status") int status,
                        @Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate,
                        Pageable pageable);

}
