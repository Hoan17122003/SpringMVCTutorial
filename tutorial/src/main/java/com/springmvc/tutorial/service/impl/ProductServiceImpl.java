package com.springmvc.tutorial.service.impl;

import com.springmvc.tutorial.model.entities.Product;
import com.springmvc.tutorial.model.repository.product.IProductRepository;
import com.springmvc.tutorial.model.repository.product.ProductRepositoryCustom;
import com.springmvc.tutorial.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    //    private final IProductRepository productRepository;
    private final IProductRepository iProductRepository;

    @Autowired
    public ProductServiceImpl(IProductRepository productRepository) {
        this.iProductRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() {
        return List.of(new Product());
    }

    public Product findProductByID(Integer productID) {
        return this.iProductRepository.findProductByID(productID);
    }

    @Override
    public Product createProduct(Product product) {
        return this.iProductRepository.save(product);
    }
}
