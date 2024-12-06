package com.springmvc.tutorial.service;

import com.springmvc.tutorial.model.entities.Product;
import com.springmvc.tutorial.model.entities.ProductAttribute;
import com.springmvc.tutorial.model.entities.ProductPhoto;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public List<Product> getAllProduct();

    public Product findProductByID(Integer productID);

    public Product createProduct(Product product);

    public void updateProduct(Product product, int productId);

    public boolean deleteProduct(int productId);

    public Long countByProductName(String productName);

    public List<Product> findByProductName(String productName, int page, int PAGE_SIZE);

    public List<Product> getPaganation(int page, int PAGE_SIZE, String searchValue, int supplierId, int categoryId,
                                       double minPrice, double maxPrice);

    public List<ProductAttribute> getAllProductAttributeByProductId(int productId);

    public List<ProductPhoto> getAllProductPhotoByProductId(int productId);

    public void updateProductPhotoByProductId(ProductPhoto productPhoto, int productId, int productPhotoId);

    public void updateProductAttributeByProductId(ProductAttribute productAttribute, int productId,
                                                  int productAttributeId);

    public void deleteProductPhotoId(int productPhotoId);

    public void deleteProductAttributeId(int productAttributeId);

    public void deleteAllProductAttributeByProductId(int productId);

    public void deleteAllProductPhotoByProductId(int productId);

    public ProductPhoto createProductPhoto(ProductPhoto productPhoto, int productID);

    public ProductAttribute createProductAttribute(ProductAttribute productAttribute, int productId);

    public ProductPhoto findProductPhotoById(int productPhotoId);

    public ProductAttribute findProductAttributeById(int productAttributeId);

    public List<Product> getAllCacheProduct(String searchValue);
}
