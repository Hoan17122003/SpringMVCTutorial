package com.springmvc.tutorial.model.entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;
import java.util.Set;
import java.util.Optional;

@Entity
@Table
public class Province {
    @Id
    @Column(name = "ProvinceName",
            columnDefinition = "nvarchar(255)")
    private String provinceName;

    public Province() {
    }

    public Province(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private Set<Customer> customers;

    @OneToMany(mappedBy = "pv", cascade = CascadeType.ALL)
    private Set<Supplier> suppliers;
}
