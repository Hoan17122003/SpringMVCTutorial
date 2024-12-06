package com.springmvc.tutorial.service.impl;

import com.springmvc.tutorial.model.entities.Category;
import com.springmvc.tutorial.model.entities.Employee;
import com.springmvc.tutorial.model.repository.employee.IEmployeeRepository;
import com.springmvc.tutorial.service.IEmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(IEmployeeRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Employee> getAllEmployee() {
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public List<Employee> getPaginatedEmployeeOfPage(Integer page, int PAGE_SIZE, String searchValue) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE); // Tạo Pageable object
        List<Employee> result = this.repository.findByFullNameContaining(searchValue, pageable).stream().toList();
        return result;
    }

    @Override
    public Long rowCount() {
        return this.repository.count();
    }

    @Override
    public Employee findEmployeeByID(Integer id) {
        return this.repository.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public int update(Employee employee, int id) {
        int result = 0;
        if (employee == null) return result;
        else {
            this.repository.findById(id).ifPresent(element -> {
                element = employee;
                this.repository.save(element);
            });
            return 1;
        }
    }

    @Override
    public void save(Employee employee) {
        this.repository.save(employee);
    }

    @Override
    public boolean deleteEmployeeById(int employeeID) {
        this.repository.deleteById(employeeID);
        return true;
    }

    public Employee findByEmail(String email) {
        return this.repository.findByEmail(email);
    }
}
