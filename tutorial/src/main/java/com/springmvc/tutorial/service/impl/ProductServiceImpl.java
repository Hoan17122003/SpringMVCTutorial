package com.springmvc.tutorial.service.impl;

import com.springmvc.tutorial.model.entities.Product;
import com.springmvc.tutorial.model.entities.ProductAttribute;
import com.springmvc.tutorial.model.entities.ProductPhoto;
import com.springmvc.tutorial.model.repository.product.IProductRepository;
import com.springmvc.tutorial.model.repository.product.ProductRepositoryCustom;
import com.springmvc.tutorial.model.repository.productattribute.IProductAttributeRepository;
import com.springmvc.tutorial.model.repository.productphoto.IProductPhotoRepository;
import com.springmvc.tutorial.service.ICategoryService;
import com.springmvc.tutorial.service.IProductService;
import com.springmvc.tutorial.service.IStorageService;
import com.springmvc.tutorial.service.ISupplierService;
import jakarta.transaction.Transactional;
import javassist.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.net.http.HttpResponse;
import java.util.Comparator;

@Service
public class ProductServiceImpl implements IProductService {

    // private final IProductRepository productRepository;
    private final IProductRepository iProductRepository;
    private final IProductPhotoRepository iProductPhotoRepository;
    private final IProductAttributeRepository iProductAttributeRepository;
    private final ISupplierService supplierService;
    private final ICategoryService categoryService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private IStorageService storageService;


    @Autowired
    public ProductServiceImpl(IProductRepository productRepository,
                              IProductAttributeRepository iProductAttributeRepository,
                              IProductPhotoRepository iProductPhotoRepository,
                              ISupplierService supplierService,
                              ICategoryService categoryService) {
        this.iProductRepository = productRepository;
        this.iProductAttributeRepository = iProductAttributeRepository;
        this.iProductPhotoRepository = iProductPhotoRepository;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
    }

    @Override
    public List<Product> getAllProduct() {
        return List.of(new Product());
    }

    public Product findProductByID(Integer productID) {
        return this.iProductRepository.findById(productID).get();
    }

    @Override
    public Product createProduct(Product product) {

        var supplier = this.supplierService.findSupplierById(product.getSupplierID());
        var category = this.categoryService.findCategoryById(product.getCategoryID());
        product.setCategory(category);
        product.setSupplier(supplier);
        product.setProductAttributes(null);
        product.setProductPhotos(null);
        return this.iProductRepository.save(product);
    }

    @Override
    public Long countByProductName(String productName) {
        return this.iProductRepository.countByProductName(productName);
    }

    @Override
    @Transactional
    public boolean deleteProduct(int productId) {
        var product = this.iProductRepository.findById(productId).get();
        if (product == null) {
            return false;
        }
        this.iProductRepository.deleteById(productId);
        return true;
    }

    @Override
    @Transactional
    public void updateProduct(Product product, int productId) {
        System.out.println("productId " + productId);
        this.iProductRepository.findById(productId).ifPresent(element -> {
            element.setProductName(product.getProductName());
            element.setProductDescription(product.getProductDescription());
            element.setSupplierID(product.getSupplierID());
            element.setCategoryID(product.getCategoryID());
            element.setUnit(product.getUnit());
            if (!product.getPhoto().isEmpty()) {
                element.setProductPhotos(element.getProductPhotos());
            }
            element.setPrice(product.getPrice());
            this.iProductRepository.save(element);
        });
    }

    @Override
    @Cacheable(value = "Product", key = "#searchValue + #page")
    public List<Product> getPaganation(int page, int PAGE_SIZE, String searchValue, int supplierId, int categoryId,
                                       double minPrice, double maxPrice) {
        // return
        // this.iProductRepository.findByProductNameAndSupplierIDAndCategoryIDAndPriceBetweenContaining(PageRequest.of(page
        // - 1, PAGE_SIZE), searchValue, supplierId, categoryId, minPrice,
        // maxPrice).stream().toList();
        var products = this.iProductRepository.findByCondition(page, PAGE_SIZE, searchValue, supplierId, categoryId,
                (float) minPrice, (float) maxPrice);
        return products;
    }

    public List<ProductAttribute> getAllProductAttributeByProductId(int productId) {
        return this.iProductAttributeRepository.findByProductID(productId).get().stream()
                .sorted(Comparator.comparing(ProductAttribute::getDisplayOrder)).toList();
    }

    public List<ProductPhoto> getAllProductPhotoByProductId(int productId) {
        return this.iProductPhotoRepository.findByProductID(productId)
                .stream()
                .sorted(Comparator.comparing(ProductPhoto::getDisplayOrder)).toList();
    }

    public void updateProductPhotoByProductId(ProductPhoto productPhoto, int productId, int productPhotoId) {
        this.iProductPhotoRepository.findByProductIDAndPhotoId(productId, productPhotoId).ifPresent(element -> {
            element.setDescription(productPhoto.getDescription());
            element.setDisplayOrder(productPhoto.getDisplayOrder());
            element.setHidden(productPhoto.isHidden());
            element.setPhoto(productPhoto.getPhoto());
            this.iProductPhotoRepository.save(element);
            // System.out.println("ProductPhotoID : " + productPhoto.getProductID());
            // productPhoto.setProductID(element.getProductID());
            // element = productPhoto;
            // this.iProductPhotoRepository.save(element);
        });
    }

    public void updateProductAttributeByProductId(ProductAttribute productAttribute, int productId,
                                                  int productAttributeId) {

        this.iProductAttributeRepository.findByIdAndProductID(productAttributeId, productId)
                .ifPresent(element -> {
                    System.out.println(new StringBuilder().append("productId :  ").append(productId)
                            .append(" - productAttributeId : ").append(productAttributeId).toString());
                    element.setAttributeName(productAttribute.getAttributeName());
                    element.setDisplayOrder(productAttribute.getDisplayOrder());
                    element.setAttributeValue(productAttribute.getAttributeValue());
                    this.iProductAttributeRepository.save(element);
                });
    }

    public void deleteProductPhotoId(int productPhotoId) {
        this.iProductPhotoRepository.deleteById(productPhotoId);
    }

    public void deleteProductAttributeId(int productAttributeId) {
        System.out.println("productAttributeid : " + productAttributeId);
        this.iProductAttributeRepository.deleteById(productAttributeId);
    }

    public void deleteAllProductAttributeByProductId(int productId) {
        this.iProductAttributeRepository.deleteByProductID(productId);
    }

    public void deleteAllProductPhotoByProductId(int productId) {
        this.iProductPhotoRepository.deleteByProductID(productId);
    }

    @Override
    public ProductPhoto createProductPhoto(ProductPhoto productPhoto, int productId) {
        System.out.println("ProductPhotoID : " + productPhoto.getProductID());
        productPhoto.setProductID(productId);
        return this.iProductPhotoRepository.save(productPhoto);
    }

    public ProductAttribute createProductAttribute(ProductAttribute productAttribute, int productId) {
        var product = this.iProductRepository.findById(productId).stream().findFirst().orElse(null);
        if (product == null)
            return null;
        productAttribute.setProduct(product);
        productAttribute.setProductID(productId);
        return this.iProductAttributeRepository.save(productAttribute);
    }

    @Override
    public ProductPhoto findProductPhotoById(int productPhotoId) {
        var productPhoto = this.iProductPhotoRepository.findById(productPhotoId).stream().findFirst().orElse(null);
        System.out.println("productPhoto : " + productPhoto.getPhotoId());
        // return
        // this.iProductPhotoRepository.findById(productPhotoId).stream().findFirst().orElse(null);
        return productPhoto;
    }

    @Override
    public ProductAttribute findProductAttributeById(int productAttributeId) {
        return this.iProductAttributeRepository.findById(productAttributeId).stream().findFirst().orElse(null);
    }

    @Override
    public List<Product> findByProductName(String productName, int page, int PAGE_SIZE) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);
        return this.iProductRepository.findByProductNameContaining(productName, pageable).stream().toList();
    }

    public List<Product> getAllCacheProduct(String searchValue) {
        var data = (Product) this.redisTemplate.opsForValue().get(searchValue);
        if (data == null) {
            return List.of();// set list empty or set list empty with method List<Product> list = Collections.<Product>emptyList();
        }
//        var result = Arrays.stream(data).mapToInt(Product::ProductValue).toArray();
        return List.of(data);
    }
}
