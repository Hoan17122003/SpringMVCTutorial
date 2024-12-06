package com.springmvc.tutorial.controller;

import com.springmvc.tutorial.model.entities.Customer;
import com.springmvc.tutorial.service.ICustomerService;
import com.springmvc.tutorial.service.IEmployeeService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(path = "/Customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ModelMapper modelMapper;

    private final int PAGE_SIZE = 10;

    @GetMapping
    public String Index(ModelMap modelMap,
                        @RequestParam(defaultValue = "") String searchValue,
                        @RequestParam(defaultValue = "1") Integer page
            , HttpSession session
    ) {
        List<Customer> customers = new ArrayList<Customer>();
//        Customer[] customerAtSesssion = (Customer[]) session.getAttribute(searchValue);
//        System.out.println("customers : " + customerAtSesssion);
//        if (customerAtSesssion != null) {
//            customers = Arrays.asList(customerAtSesssion);
//        }
//
//        if (session.getAttribute(searchValue) == null) {
//            session.setAttribute(searchValue.toLowerCase().trim(), customers);
//        }
        customers = this.customerService.getPaginatedCustomerOfPage(page, PAGE_SIZE, searchValue).stream().toList();
        Long rowCount = this.customerService.countCustomer(searchValue);
        Double count = (double) rowCount / PAGE_SIZE;
        int pageCount = (int) Math.ceil(count);

        modelMap.addAttribute("rowCount", rowCount);
        modelMap.addAttribute("pageCount", pageCount);
        modelMap.addAttribute("customers", customers);
        modelMap.addAttribute("searchValue", searchValue);
        modelMap.addAttribute("customerSize", customers.size());
        return "Customer/Index";
    }

    @GetMapping("/Edit/{id}")
    public String Edit(@PathVariable("id") Integer customerId, ModelMap modelMap) {
        Customer customer = this.customerService.findByCustomerId(customerId);
        modelMap.addAttribute("customer", customer);
        return "Customer/Edit";
    }

    @GetMapping("/Create")
    public String Create(ModelMap modelMap) {
        modelMap.addAttribute("customer", new Customer());
        return "Customer/Edit";
    }

    @PostMapping("/Save")
    public String Save(@ModelAttribute("customer") Customer customer, @RequestParam("customerId") Integer customerId) {
        if (customerId == null) {
            this.customerService.saveCustomer(customer);
        } else {
            this.customerService.editCustomer(customer, customerId);
        }
        return "redirect:/Customer";
    }
}
