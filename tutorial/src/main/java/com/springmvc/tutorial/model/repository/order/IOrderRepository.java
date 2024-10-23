package com.springmvc.tutorial.model.repository.order;

import com.springmvc.tutorial.model.entities.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer>, IOrderRepositoryCustom {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into orderstatus (status,description) values(:status,:description)")
    void saveOrderStatus(@Param("status") Integer status, @Param("description") String description);
}
