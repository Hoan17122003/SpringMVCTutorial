package com.springmvc.tutorial.model.repository.customer;

import com.springmvc.tutorial.model.entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>, ICustomerRepositoryCustom {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into province(ProvinceName) values(:provinceName) ")
    void insertDataWithProvince(@Param("provinceName") String provinceName);
}
