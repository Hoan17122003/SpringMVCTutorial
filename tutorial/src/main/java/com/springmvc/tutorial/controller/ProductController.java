package com.springmvc.tutorial.controller;

import com.springmvc.tutorial.dto.request.ProductDTO;
import com.springmvc.tutorial.model.entities.*;
import com.springmvc.tutorial.service.ICategoryService;
import com.springmvc.tutorial.service.IProductService;
import com.springmvc.tutorial.service.IStorageService;

import jakarta.validation.Valid;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(path = "/Product")
public class ProductController {

    private final IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IStorageService storageService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    private static final int PAGE_SIZE = 10;

    @GetMapping
    public String Index(ModelMap modelMap,
                        @RequestParam(value = "SearchValue", required = false, defaultValue = "") String searchValue,
                        @RequestParam(value = "MinPrice", required = false, defaultValue = "0.0") Double minPrice,
                        @RequestParam(value = "MaxPrice", required = false, defaultValue = "0.0") Double maxPrice,
                        @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                        @RequestParam(value = "CategoryId", required = false, defaultValue = "0") Integer categoryId,
                        @RequestParam(value = "SupplierId", required = false, defaultValue = "0") Integer supplierId,
                        @RequestParam(value = "message", required = false) String message) {

        var products = this.productService.getPaganation(page, PAGE_SIZE, searchValue, supplierId, categoryId, minPrice,
                maxPrice);
        Long rowCount = this.productService.countByProductName(searchValue);
        Double count = (double) rowCount / PAGE_SIZE;
        int pageCount = (int) Math.ceil(count);
        modelMap.addAttribute("title", "Product Page");
        modelMap.addAttribute("SearchValue", searchValue);
        modelMap.addAttribute("rowCount", rowCount);
        modelMap.addAttribute("pageCount", pageCount);
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("MinPrice", minPrice);
        modelMap.addAttribute("MaxPrice", maxPrice);
        modelMap.addAttribute("CategoryId", categoryId);
        modelMap.addAttribute("SupplierId", supplierId);
        modelMap.addAttribute("message", message);
        return "Product/Index";
    }

    // @GetMapping(path = "/get/{id}")
    // public ResponseEntity<Object> getProductID(@PathVariable("id") int productID)
    // {
    // System.out.println("ID : " + productID);
    // var product = this.productService.findProductByID(productID);
    // return new ResponseEntity<Object>(product, HttpStatus.OK);
    // }

    @GetMapping(path = "/Edit/{id}")
    public String EditProduct(ModelMap modelMap, @PathVariable("id") int id) {
        Product product = this.productService.findProductByID(id);
        List<ProductPhoto> productPhoto = this.productService.getAllProductPhotoByProductId(id);
        List<ProductAttribute> productAttributes = this.productService.getAllProductAttributeByProductId(id);

        modelMap.addAttribute("product", product);
        modelMap.addAttribute("productPhotos", productPhoto);
        modelMap.addAttribute("productAttributes", productAttributes);

        return "Product/Edit";
    }

    // call DTO Product
    @GetMapping(path = "/Create")
    public String CreateProduct(ModelMap modelMap) {
        Product product = new Product();
        ProductAttribute productAttribute = new ProductAttribute();
        ProductPhoto productPhoto = new ProductPhoto();
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("ProductAttribute", productAttribute);
        modelMap.addAttribute("ProductPhoto", productPhoto);
        return "Product/Edit";
    }

    @GetMapping(path = "/SearchProduct")
    public String SearchProduct(ModelMap modelMap,
                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue) {
        var products = this.productService.findByProductName(searchValue, page, PAGE_SIZE);
        products.stream().forEach(product -> {
            System.out.println(new StringBuilder().append("productID : ").append(product.getProductID()));
        });
        modelMap.addAttribute("products", products);
        return "/Order/fragments/SearchProduct :: searchProduct";
    }

    @PostMapping(path = "/Save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object CreateProductPost(@ModelAttribute("product") @Valid Product product,
                                    @RequestParam(value = "ProductID", defaultValue = "0") Integer productId, ModelMap modelMap,
                                    @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile,
                                    BindingResult bindingResult) {
        String message;
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("product", product);
            modelMap.addAttribute("errors", bindingResult.getAllErrors());
            return "Product/Edit";
        }
        if (uploadFile != null && !uploadFile.isEmpty()) {
            String urlFile = this.storageService.storeFile(uploadFile);
            product.setPhoto(urlFile);
        }
        if (productId != null && productId != 0) {
            this.productService.updateProduct(product, productId);
            message = "sửa thành công";
        } else {
            this.productService.createProduct(product);
            message = "Thêm thành công";
        }
        modelMap.addAttribute("message", message);
        return new ModelAndView("redirect:/Product", modelMap);
    }

    @PostMapping(path = "/Delete/{productId}")
    public ModelAndView DeleteProduct(ModelMap modelMap, @PathVariable("productId") Integer productId) {
        String message = "xoá thất bại";
        if (this.productService.deleteProduct(productId)) {
            message = "Xoá thành công";
        }
        return new ModelAndView("redirect:/Product", modelMap.addAttribute("message", message));
    }

    // method handle for 3 http method : add, delete ,edit ProductPhoto
    @PostMapping(path = "/Photo/{productId}")
    public ModelAndView HandleMethodHttpProductPhoto(
            @PathVariable("productId") Integer productId,
            @ModelAttribute("productPhoto") ProductPhoto productPhoto,
            @RequestParam(value = "photoId", required = false, defaultValue = "0") Integer photoId,
            @RequestParam("method") String method,
            @RequestParam(value = "uploadPhoto", required = false) MultipartFile file,
            ModelMap modelMap) {
        String message = "";
        var productPhotoOld = this.productService.findProductPhotoById(photoId);
        if (file != null) {
            System.out.println("testhehehe : " + method);
            var photoUrl = this.storageService.storeFile(file);
            productPhoto.setPhoto(photoUrl);
        }
        if (method.toLowerCase().equals("add")) {
            this.productService.createProductPhoto(productPhoto, productId);
            message = "Thêm ảnh thảnh công";
        } else if (method.toLowerCase().equals("edit")) {
            if (file.getOriginalFilename() != productPhotoOld.getPhoto()) {
                this.storageService.deleteFileByUrl(productPhotoOld.getPhoto());
            }
            this.productService.updateProductPhotoByProductId(productPhoto, productId, photoId);
            message = "Update ảnh thành công";
        } else if (method.toLowerCase().equals("delete")) {
            System.out.println("ProductPhotohehehehe : " + productPhoto);
            this.storageService.deleteFileByUrl(productPhotoOld.getPhoto());
            this.productService.deleteProductPhotoId(photoId);
            message = "Xoá ảnh thành công";
        }
        modelMap.addAttribute("message", message);
        return new ModelAndView(new StringBuilder().append("redirect:/Product/Edit/").append(productId).toString(),
                modelMap);
    }

    @GetMapping(path = "/Photo/{productId}")
    public String HandleProductPhotoByMethod(@RequestParam("method") String method, ModelMap modelMap,
                                             @RequestParam(value = "photoId", required = false, defaultValue = "0") Integer photoId,
                                             @PathVariable("productId") Integer productId) {
        if (method.toLowerCase().equals("add")) {
            ProductPhoto productPhoto = new ProductPhoto();
            modelMap.addAttribute("productPhoto", productPhoto);
            modelMap.addAttribute("productId", productId);
            modelMap.addAttribute("method", method);
        } else if (method.toLowerCase().equals("edit")) {
            var productPhoto = this.productService.findProductPhotoById(photoId);
            modelMap.addAttribute("productPhoto", productPhoto);
            modelMap.addAttribute("productId", productId);
            modelMap.addAttribute("method", method);
        }
        return "Product/Photo";
    }

    @PostMapping(path = "/Attribute/{productId}")
    public ModelAndView HandleMethodHttpProductAttribute(ModelMap modelMap,
                                                         @PathVariable("productId") Integer productId,
                                                         @RequestParam("method") String method,
                                                         @RequestParam(value = "attributeID", required = false, defaultValue = "0") Integer attributeId,
                                                         @Valid @ModelAttribute ProductAttribute productAttribute) {
        String message = "";
        if (method.toLowerCase().equals("add")) {
            this.productService.createProductAttribute(productAttribute, productId);
            message = "Thêm thuộc tínhh thành công";
        } else if (method.toLowerCase().equals("edit")) {
            message = "Cập nhật thuộc tính thành công";
            this.productService.updateProductAttributeByProductId(productAttribute, productId, attributeId);
        } else if (method.toLowerCase().equals("delete")) {
            message = "Xoá thuộc tính thành công";
            this.productService.deleteProductAttributeId(attributeId);
        }
        return new ModelAndView(new StringBuilder().append("redirect:/Product/Edit/").append(productId).toString(),
                modelMap.addAttribute("message", message));
    }

    @GetMapping(path = "/Attribute/{productId}")
    public String HandleProductAttributeByMethod(@RequestParam("method") String method,
                                                 ModelMap modelMap,
                                                 @RequestParam(value = "attributeId", required = false, defaultValue = "0") Integer attributeId,
                                                 @PathVariable("productId") Integer productID) {

        if (method.toLowerCase().equals("add")) {
            ProductAttribute productAttribute = new ProductAttribute();
            modelMap.addAttribute("productAttribute", productAttribute);
            modelMap.addAttribute("productID", productID);
            modelMap.addAttribute("method", method);
        } else if (method.toLowerCase().equals("edit")) {
            var productAttribute = this.productService.findProductAttributeById(attributeId);
            modelMap.addAttribute("productAttribute", productAttribute);
            modelMap.addAttribute("productID", productID);
            modelMap.addAttribute("method", method);
        }
        return "Product/Attribute";
    }

    @GetMapping("/CacheSearch")
    public synchronized ResponseEntity<List<Product>> CacheSearch(@RequestParam("SearchValue") String searchvalue) {
        var result = this.productService.getAllCacheProduct(searchvalue);
        return ResponseEntity.ok(result);
    }

}
