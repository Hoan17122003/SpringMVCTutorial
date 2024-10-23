package com.springmvc.tutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/Customer")
public class CustomerController {

    @RequestMapping
    public String Index() {
        return "Customer/Index";
    }
}
