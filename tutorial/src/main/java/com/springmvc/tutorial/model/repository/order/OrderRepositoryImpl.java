package com.springmvc.tutorial.model.repository.order;

import com.springmvc.tutorial.model.entities.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.List;

@Transactional
public class OrderRepositoryImpl implements IOrderRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public void insertDataWithFile(List<Order> entity) {

    }
}
