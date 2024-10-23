package com.springmvc.tutorial.model.entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "Suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            columnDefinition = "int"
    )
    private int supplierID;

    @Column(
            name = "SupplierName",
            columnDefinition = "nvarchar(100)",
            nullable = false,
            unique = true
    )
    private String supplierName;

    @Column(
            name = "ContactName",
            nullable = false,
            columnDefinition = "nvarchar(255)"
    )
    private String contactName;

    @Column(
            name = "Province",
            nullable = false,
            columnDefinition = "nvarchar(255)"
    )
    private String province;

    @Column(
            name = "Address",
            columnDefinition = "nvarchar(255)"
            , nullable = false
    )
    private String address;

    @Column(
            name = "Phone",
            nullable = false,
            unique = true,
            columnDefinition = "nvarchar(20)"
    )
    private String phone;

    @Column(
            name = "Email",
            nullable = false,
            unique = true,
            columnDefinition = "nvarchar(255)"
    )
    private String email;

    @Column(
            name = "Logo",
            columnDefinition = "nvarchar(500)"
    )
    private String logo;

    public Supplier() {
    }

    public Supplier(String supplierName, String contactName, String province, String address, String phone, String email, String logo) {
        this.supplierName = supplierName;
        this.contactName = contactName;
        this.province = province;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.logo = logo;
    }

    //relations
    @ManyToOne
    @JoinColumn(name = "ProvinceName", insertable = false, updatable = false)
    private Province pv;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Product> products;


    public int getSupplierID() {
        return supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getProvince() {
        return province;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getLogo() {
        return logo;
    }

    public Province getPv() {
        return pv;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setPv(Province pv) {
        this.pv = pv;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
