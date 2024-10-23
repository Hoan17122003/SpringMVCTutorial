package com.springmvc.tutorial.controller;

import com.springmvc.tutorial.dto.request.ProductDTO;
import com.springmvc.tutorial.model.entities.Category;
import com.springmvc.tutorial.model.entities.Product;
import com.springmvc.tutorial.model.entities.ProductAttribute;
import com.springmvc.tutorial.model.entities.Supplier;
import com.springmvc.tutorial.service.ICategoryService;
import com.springmvc.tutorial.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;
import java.util.List;


@Controller
@RequestMapping(path = "/Product")
public class ProductController {

    private final IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }


    @GetMapping(path = "")
    public synchronized String Index(ModelMap modelMap) {
        Category category = new Category();
        Supplier supplier = new Supplier();
        System.out.println("hehe");
        modelMap.addAttribute("categoty", category);
        modelMap.addAttribute("supplier", supplier);
        var products = this.productService.getAllProduct();
        modelMap.addAttribute("title", "Product Page");
        modelMap.addAttribute("products", products);
        return "Product/Index";
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Object> getProductID(@PathVariable("id") int productID) {
        System.out.println("ID : " + productID);
        var product = this.productService.findProductByID(productID);
        return new ResponseEntity<Object>(product, HttpStatus.OK);
    }

    @GetMapping(path = "/Edit/{id}")
    public String EditProduct(ModelMap modelMap, @PathVariable("id") int id) {
        Product product = this.productService.findProductByID(id);
        ProductDTO productDTO = new ProductDTO();
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("productDTO", productDTO);
        return "Product/Edit";
    }

    // call DTO Product
    @GetMapping(path = "/Create")
    public String CreateProduct(ModelMap modelMap) {
        Product product = new Product();
        modelMap.addAttribute("product", product);
        List<Category> category = this.categoryService.getAllCategory();
        Supplier supplier = new Supplier();
        modelMap.addAttribute("categories", category);
        modelMap.addAttribute("supplier", supplier);
        return "Product/Edit";
    }

    @PostMapping(path = "Create")
    public String CreateProductPost(@RequestBody() Product product, ModelMap modelMap) {
        if (product == null) {

        }

        return "Product/Create";
    }


}
