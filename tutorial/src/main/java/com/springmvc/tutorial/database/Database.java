package com.springmvc.tutorial.database;

import com.springmvc.tutorial.model.entities.*;
import com.springmvc.tutorial.model.repository.category.CategoryFactoryData;
import com.springmvc.tutorial.model.repository.category.ICategoryRepository;
import com.springmvc.tutorial.model.repository.customer.CustomerFactoryData;
import com.springmvc.tutorial.model.repository.customer.ICustomerRepository;
import com.springmvc.tutorial.model.repository.employee.EmployeeFactoryData;
import com.springmvc.tutorial.model.repository.employee.IEmployeeRepository;
import com.springmvc.tutorial.model.repository.order.IOrderRepository;
import com.springmvc.tutorial.model.repository.order.OrderFactoryData;
import com.springmvc.tutorial.model.repository.product.IProductRepository;
import com.springmvc.tutorial.model.repository.product.ProductFactoryData;
import com.springmvc.tutorial.model.repository.productattribute.IProductAttributeRepository;
import com.springmvc.tutorial.model.repository.productphoto.IProductPhotoRepository;
import com.springmvc.tutorial.model.repository.shipper.IShipperRepository;

import com.springmvc.tutorial.model.repository.shipper.ShipperFactoryData;
import com.springmvc.tutorial.model.repository.supplier.ISupplierRepository;
import com.springmvc.tutorial.model.repository.supplier.SupplierFactoryData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;


@Configuration
public class Database {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ICategoryRepository categoryRepository,
                                   ICustomerRepository customerRepository,
                                   IEmployeeRepository employeeRepository,
                                   IOrderRepository orderRepository,
                                   IProductAttributeRepository productAttributeRepository,
                                   IProductPhotoRepository productPhotoRepository,
                                   IShipperRepository shipperRepository,
                                   IProductRepository productRepository,
                                   ISupplierRepository supplierRepository
    ) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                logger.info("hibernate and jpa starting...");
                // insert data customer categories ,... method handle getDataWithFile and save with repository
//                List<Customer> customers = new CustomerFactoryData().getDataWithFile();
//                customers.stream().forEach(element -> {
//                    System.out.println("password : " + element.getPassword());
//                    System.out.println("element : " + element.toString());
//                });
//                logger.info("insert data for customer and province : " + customerRepository.saveAll(customers));
//                List<Employee> employees = new EmployeeFactoryData().getDataWithFile();
//                // insert employee  n-data
////                logger.info("insert employee data : " + employeeRepository.saveAll(employees));
//                List<Shipper> shippers = new ShipperFactoryData().getDataWithFile();
//                // insert shipper n-data
//                logger.info("insert shipper data : " + shipperRepository.saveAll(shippers));
//                List<Supplier> suppliers = new SupplierFactoryData().getDataWithFile();
//                logger.info("insert suppliers data : " + supplierRepository.saveAll(suppliers));
//                List<Product> products = new ProductFactoryData().getDataWithFile();
//                products.stream().forEach(element -> {
//                    Optional<Category> category = categoryRepository.findById(element.getCategoryID());
//                    element.setCategory(category.stream().findFirst().orElse(null));
//                    Optional<Supplier> supplier = supplierRepository.findById(element.getSupplierID());
//                    element.setSupplier(supplier.stream().findFirst().orElse(null));
//                    element.setProductAttributes(null);
//                    element.setProductPhotos(null);
//                });
//                logger.info("insert product data : " + productRepository.saveAll(products));
////                List<OrderStatus> orderStatuses = new OrderFactoryData().getDataWithFileOrderStatus();
////                orderStatuses.stream().forEach(element -> orderRepository.saveOrderStatus(element.getStatus(), element.getDescription()));
//                List<Order> orders = new OrderFactoryData().getDataWithFileOrder();
//                logger.info("insert order data : " + orderRepository.saveAll(orders));
            }
        };
    }
}
