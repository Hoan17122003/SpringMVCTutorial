package com.springmvc.tutorial.service;

import com.springmvc.tutorial.model.entities.Category;
import com.springmvc.tutorial.model.entities.ProductAttribute;
import com.springmvc.tutorial.model.entities.ProductPhoto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    public List<Category> getPaginatedCategoriesOfPage(Integer page, int PAGE_SIZE, String searchValue);


    public List<Category> getAllCategory();

    public Long RowCount();

    public void updateCategory(Category category, int categoryID);

    public void saveCategory(Category category);

    public Category findCategoryById(int id);

    public void deleteCategory(int id);

   
}
