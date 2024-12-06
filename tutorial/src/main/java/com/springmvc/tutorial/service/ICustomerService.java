package com.springmvc.tutorial.service;

import com.springmvc.tutorial.model.entities.Customer;
import com.springmvc.tutorial.model.entities.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {

    Page<Customer> getPaginatedCustomerOfPage(int page, int PAGE_SIZE, String searchValue);

    void saveCustomer(Customer customer);

    boolean deleteCustomer(int customerId);

    boolean editCustomer(Customer customer, int customerId);

    Long countCustomer(String searchValue);

    Customer findByCustomerId(int customerId);

}
