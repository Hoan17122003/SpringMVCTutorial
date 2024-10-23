package com.springmvc.tutorial.service;

import com.springmvc.tutorial.model.entities.Category;
import com.springmvc.tutorial.model.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<Employee> getAllEmployee();

    List<Employee> getPaginatedEmployeeOfPage(Integer page, int PAGE_SIZE, String searchValue);

    Long rowCount();

    Employee findEmployeeByID(Integer id);

    int update(Employee employee, int id);

    void save(Employee employee);
}
