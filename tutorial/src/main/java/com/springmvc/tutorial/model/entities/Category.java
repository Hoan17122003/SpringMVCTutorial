package com.springmvc.tutorial.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Categories")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L; // Thêm serialVersionUID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private int categoryId;

    @Column(name = "CategoryName")
    private String categoryName;
    @Column(name = "Description")
    private String Description;

    public Category() {
    }

    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        Description = description;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return Description;
    }

    // relationship in Categories
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public List<Product> products;

    @Override
    public String toString() {
        return this.getCategoryName() + " " + this.getDescription();
    }

}
