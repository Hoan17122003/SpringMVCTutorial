package com.springmvc.tutorial.model.repository.employee;

import com.springmvc.tutorial.model.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.List;

@Transactional
public class EmployeeRepositoryImpl implements IEmployeeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public void insertDataWithFile(List<Employee> entity) {

    }


}
