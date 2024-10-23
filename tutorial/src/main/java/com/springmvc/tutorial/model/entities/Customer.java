package com.springmvc.tutorial.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;

@Entity
@Table
//@Target(value = {METHOD, FIELD})
//@Retention(value = RUNTIME)
public class Customer {

    @Id
    @Column(
            name = "CustomerID"
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;

    @Column(
            name = "CustomerName",
            columnDefinition = "nvarchar(30)",
            nullable = false
    )
    private String customerName;

    @Column(
            name = "ContactName",
            nullable = false,
            columnDefinition = "nvarchar(100)"
    )
    private String contactName;

    @Column(
            nullable = false
            , columnDefinition = "nvarchar(255)"
    )
    private String Province;

    @Column(
            name = "Address",
            columnDefinition = "nvarchar(255)",
            nullable = false
    )
    private String address;

    @Column(
            name = "Phone",
            nullable = false,
            columnDefinition = "nvarchar(20)",
            unique = true
    )
    private String phone;

    @Column(
            name = "Email",
            columnDefinition = "nvarchar(255)",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(
            name = "Password",
            nullable = false,
            columnDefinition = "nvarchar(255)"

    )
    private String password;
    @Column(
            name = "IsLocked",
            nullable = false,
            columnDefinition = "bit"
    )
    private boolean isLocked;

    public Customer() {
    }

    public Customer(String customerName, String contactName, String province, String address, String phone, String email, String password, boolean isLocked) {
        this.customerName = customerName;
        this.contactName = contactName;
        Province = province;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.isLocked = isLocked;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getProvince() {
        return Province;
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

    public String getPassword() {
        return password;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setProvince(String province) {
        Province = province;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    @Override
    public String toString() {
        return this.customerName + "-" +
                this.contactName + "-" +
                Province + "-" +
                this.address + "-" +
                this.phone + "-" +
                this.email + "-" +
                this.password + "-" +
                this.isLocked + "-";
    }

    //relations
    @ManyToOne
    @JoinColumn(
            name = "Province",
            updatable = false,
            insertable = false
    )
    private Province province;

    @OneToMany(
            mappedBy = "customerRelations",
            cascade = CascadeType.ALL
    )
    private List<Order> orders;
}
