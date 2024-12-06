package com.springmvc.tutorial.model.repository.shipper;

import com.springmvc.tutorial.model.entities.Shipper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.*;

@Repository
public interface IShipperRepository extends JpaRepository<Shipper, Integer> {

    Page<Shipper> findByShipperNameContaining(Pageable pageable, String searchValue);

    @Query(nativeQuery = true, value = "select count(Phone) from shippers where ShipperName like %:searchValue%")
    Long countDistinctByShipperName(@Param("searchValue") String searchValue);
}
