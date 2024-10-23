package com.springmvc.tutorial.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(
//            name = "ProductID",
//            nullable = false,
//            columnDefinition = "int"
//    )
    private int ProductID;

    @Column(
            name = "ProductName",
            columnDefinition = "nvarchar(255)",
            nullable = false
    )
    @NotEmpty
    private String productName;

    @Column(
            name = "ProductDescription",
            columnDefinition = "nvarchar(255)"
    )
//    @NotEmpty(message = "not null value")
    private String productDescription;

    @Column(
            name = "SupplierID",
            columnDefinition = "int"

    )
    @NotNull(message = "supplier is not null")
    private int supplierID;

    @Column(
            name = "CategoryID",
            columnDefinition = "int"
    )
    @NotNull(message = "category is not null ")
    private int categoryID;

    @Column(
            name = "Unit",
            columnDefinition = "nvarchar(255)",
            nullable = false
    )
    @NotEmpty
    private String unit;

    @Column(
            name = "Price",
            columnDefinition = "decimal(15,2)",
            nullable = false
    )
//    @NotEmpty
//    @NotBlank(message = "price is not a string")
    private double price;

    @Column(
            name = "Photo",
            columnDefinition = "nvarchar(255)"
    )
    private String Photo;

    @Column(
            name = "IsSelling",
            columnDefinition = "bit"
    )
    @NotNull(message = "is selling not null ")
    private boolean isSelling;

    public Product() {
    }

    public Product(String productName, String productDescription, int supplierID, int categoryID, String unit, double price, String photo, boolean isSelling) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.supplierID = supplierID;
        this.categoryID = categoryID;
        this.unit = unit;
        this.price = price;
        Photo = photo;
        this.isSelling = isSelling;
    }

    public int getProductID() {
        return ProductID;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getUnit() {
        return unit;
    }

    public double getPrice() {
        return price;
    }

    public String getPhoto() {
        return Photo;
    }

    public boolean isSelling() {
        return isSelling;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public void setSelling(boolean selling) {
        isSelling = selling;
    }

    // relationship for Products
    @ManyToOne
    @JoinColumn(name = "CategoryID", updatable = false, insertable = false)
    private Category category;

    @OneToMany(
            mappedBy = "product"
            , cascade = CascadeType.ALL
    )
    private List<ProductPhoto> productPhotos;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductAttribute> productAttributes;

    @ManyToOne
    @JoinColumn(
            name = "SupplierID",
            updatable = false,
            insertable = false
    )
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    public Category getCategory() {
        return category;
    }

    public List<ProductPhoto> getProductPhotos() {
        return productPhotos;
    }

    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setProductPhotos(List<ProductPhoto> productPhotos) {
        this.productPhotos = productPhotos;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
