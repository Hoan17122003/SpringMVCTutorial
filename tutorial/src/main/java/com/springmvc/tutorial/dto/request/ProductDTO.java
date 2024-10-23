package com.springmvc.tutorial.dto.request;

import com.springmvc.tutorial.model.entities.Product;
import com.springmvc.tutorial.model.entities.ProductAttribute;
import com.springmvc.tutorial.model.entities.ProductPhoto;

public class ProductDTO {

    public Product product;
    public ProductAttribute productAttribute;
    public ProductPhoto productPhoto;

    public ProductDTO() {
        this.product = new Product();
        this.productAttribute = new ProductAttribute();
        this.productPhoto = new ProductPhoto();
    }

}
