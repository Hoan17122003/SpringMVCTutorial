package com.springmvc.tutorial.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ProductAttributes")
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "AttributeID"
    )
    private int attributeID;

    @Column(
            name = "productID",
            nullable = false
    )
    private int productID;

    @Column(
            name = "AttributeName",
            nullable = false
    )
    private String attributeName;

    @Column(
            name = "AttributeValue",
            columnDefinition = "nvarchar(255)",
            nullable = false
    )
    private String attributeValue;

    @Column(
            name = "DisplayOrder",
            columnDefinition = "int",
            nullable = false
    )
    private int displayOrder;

    public ProductAttribute() {
    }

    public ProductAttribute(int productID, String attributeName, String attributeValue, int displayOrder, Product product) {
        this.productID = productID;
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
        this.displayOrder = displayOrder;
        this.product = product;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAttributeID() {
        return attributeID;
    }

    public int getProductID() {
        return productID;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public Product getProduct() {
        return product;
    }
    // relations in ProductAttributes

    @ManyToOne
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID", insertable = false, updatable = false)
    public Product product;

}
