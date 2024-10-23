package com.springmvc.tutorial.model.repository.shipper;

import com.springmvc.tutorial.model.entities.Shipper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.List;

@Transactional
public class ShipperRepositoryImpl implements IShipperRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public void insertDataWithFile(List<Shipper> entity) {

    }
}
