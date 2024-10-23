package com.springmvc.tutorial.model.repository.product.impl;

import com.springmvc.tutorial.model.entities.Product;
import com.springmvc.tutorial.model.repository.product.IProductRepository;
import com.springmvc.tutorial.model.repository.product.ProductRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Transactional
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    public void insertDataWithFile(List<Product> entity) {

    }
}
