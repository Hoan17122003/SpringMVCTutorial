package com.springmvc.tutorial.model.entities;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "ProductPhotos")
public class ProductPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "PhotoID",
            columnDefinition = "int"
    )
    private int photoId;

    @Column(
            name = "productID",
            nullable = false,
            columnDefinition = "int"
    )
    private int productID;

    @Column(
            name = "Photo",
            nullable = false,
            columnDefinition = "nvarchar(255)"
    )
    private String photo;

    @Column(
            name = "Description",
            nullable = false,
            columnDefinition = "nvarchar(255)"
    )
    private String description;

    @Column(
            name = "DisplayOrder",
            nullable = false
    )
    private int DisplayOrder;

    @Column(
            name = "IsHidden",
            columnDefinition = "bit",
            nullable = false
    )
    private boolean isHidden;

    //----------- relations in ProductPhotos

    @ManyToOne
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID", updatable = false, insertable = false)
    private Product product;

    public ProductPhoto() {
    }

    public ProductPhoto(int productID, String photo, String description, int displayOrder, boolean isHidden) {
        this.productID = productID;
        this.photo = photo;
        this.description = description;
        DisplayOrder = displayOrder;
        this.isHidden = isHidden;
    }

    public int getPhotoId() {
        return photoId;
    }

    public int getProductID() {
        return productID;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDescription() {
        return description;
    }

    public int getDisplayOrder() {
        return DisplayOrder;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public Product getProduct() {
        return product;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDisplayOrder(int displayOrder) {
        DisplayOrder = displayOrder;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
