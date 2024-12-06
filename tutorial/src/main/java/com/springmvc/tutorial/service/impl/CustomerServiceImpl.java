package com.springmvc.tutorial.service.impl;

import com.springmvc.tutorial.model.entities.Customer;
import com.springmvc.tutorial.model.repository.customer.ICustomerRepository;
import com.springmvc.tutorial.service.ICustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository repository;

    @Transactional
    @Override
    public boolean deleteCustomer(int customerId) {
        var customer = this.repository.findById(customerId);
        if (customer == null)
            return false;
        this.repository.deleteById(customerId);
        return true;
    }

    @Transactional
    @Override
    public boolean editCustomer(Customer customer, int customerId) {
        if (customer == null)
            return false;
        this.repository.findById(customerId).ifPresent(element -> {
            element = customer;
            this.repository.save(element);
        });
        return true;
    }

    @Override
    public Long countCustomer(String searchValue) {
        return this.repository.countCustomerCondition(searchValue);
    }

    @Transactional
    @Override
    public Page<Customer> getPaginatedCustomerOfPage(int page, int PAGE_SIZE, String searchValue) {
        Pageable pagable = PageRequest.of(page - 1, PAGE_SIZE);
        Page<Customer> result = this.repository.findByCustomerNameContaining(searchValue, pagable);
        return result;
    }

    @Transactional
    @Override
    public void saveCustomer(Customer customer) {
        this.repository.save(customer);
    }

    @Override
    public Customer findByCustomerId(int customerId) {
        return this.repository.findById(customerId).stream().findFirst().orElse(null);
    }

    public List<Customer> getAllCustomer() {
        var customers = this.repository.findAll();
        return customers;
    }
}
