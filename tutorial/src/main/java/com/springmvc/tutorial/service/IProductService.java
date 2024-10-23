package com.springmvc.tutorial.service;

import com.springmvc.tutorial.model.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public List<Product> getAllProduct();

    public Product findProductByID(Integer productID);

    public Product createProduct(Product product);
}
