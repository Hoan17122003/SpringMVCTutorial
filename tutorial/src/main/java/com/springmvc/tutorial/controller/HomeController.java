package com.springmvc.tutorial.controller;

import com.springmvc.tutorial.model.entities.Category;
import com.springmvc.tutorial.model.entities.Product;
import com.springmvc.tutorial.model.entities.Supplier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
public class HomeController {
    @GetMapping(path = "/")
    public String Index(ModelMap modelMap) {
        modelMap.addAttribute("title", "Home title page");

        return "Home/Index";
    }
}
