package com.springmvc.tutorial.model.repository.customer;

import com.springmvc.tutorial.model.entities.Customer;
import com.springmvc.tutorial.model.entities.Province;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerFactoryData {
    private final String urlData = "src/main/java/datawithdb/customers.txt";
    private final String urlDataProvice = "src/main/java/datawithdb/provinces.txt";

    public List<Customer> getDataWithFile() throws IOException {
        FileInputStream fis = new FileInputStream(urlData);
        Scanner sc = new Scanner(fis);
        List<Customer> customers = new ArrayList<Customer>();
        while (sc.hasNextLine()) {
            String line[] = sc.nextLine().split(";");
            Customer customer = new Customer();
            customer.setCustomerName(line[0].toString());
            customer.setContactName(line[1].toString());
            customer.setProvince(line[2].toString());
            customer.setPhone(line[3].toString());
            customer.setEmail(line[4].toString());
            customer.setPassword(line[5].toString());
            customer.setLocked(Boolean.parseBoolean(line[6]));
            //insert data default " "
            customer.setAddress(" ");
            customers.add(customer);
        }
        sc.close();
        fis.close();
        return customers;
    }

    public List<Province> getDataWithFileProvince() throws IOException {
        FileInputStream fis = new FileInputStream(this.urlDataProvice);
        Scanner sc = new Scanner(fis);
        List<Province> provinces = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            provinces.add(new Province(line.toString()));
        }
        sc.close();
        fis.close();
        return provinces;
    }

}
