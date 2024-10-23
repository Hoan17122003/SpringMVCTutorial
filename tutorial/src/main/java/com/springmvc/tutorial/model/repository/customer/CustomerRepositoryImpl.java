package com.springmvc.tutorial.model.repository.customer;

import com.springmvc.tutorial.model.entities.Customer;
import com.springmvc.tutorial.model.entities.Province;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Transactional
public class CustomerRepositoryImpl implements ICustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

}
