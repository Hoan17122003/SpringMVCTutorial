package com.springmvc.tutorial.model.repository.shipper;

import com.springmvc.tutorial.model.entities.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShipperRepository extends JpaRepository<Shipper, Integer> {
}
