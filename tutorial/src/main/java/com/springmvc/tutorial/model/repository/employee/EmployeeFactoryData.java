package com.springmvc.tutorial.model.repository.employee;

import com.springmvc.tutorial.enums.ERoleName;
import com.springmvc.tutorial.model.entities.Employee;
import org.hibernate.engine.internal.Collections;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class EmployeeFactoryData {
    private String urlData = "src/main/java/datawithdb/employees.txt";

    public List<Employee> getDataWithFile() throws IOException {
        FileInputStream fis = new FileInputStream(this.urlData);
        Scanner sc = new Scanner(fis);
        List<Employee> employees = new ArrayList<Employee>();
        while (sc.hasNextLine()) {
            String line[] = sc.nextLine().split(";");
            Employee employee = new Employee();
            employee.setFullName(line[0]);
            employee.setDOB(LocalDate.parse(line[1].toString()));
            employee.setAddress(line[2]);
            employee.setPhone(line[3]);
            employee.setEmail(line[4]);
            employee.setPassword(line[5]);
            employee.setPhoto(" ");
            employee.setWorking(Boolean.parseBoolean(line[7]));
            List<String> roleNamesValue = Arrays.stream(line[8].split(",")).toList();
            List<ERoleName> roleNames = new ArrayList<ERoleName>();
            roleNamesValue.stream().forEach(element -> {
                roleNames.add(ERoleName.valueOf(element.toUpperCase()));
            });
            employee.setRoleNames(roleNames);
            employees.add(employee);
        }
        sc.close();
        fis.close();
        return employees;
    }
}
