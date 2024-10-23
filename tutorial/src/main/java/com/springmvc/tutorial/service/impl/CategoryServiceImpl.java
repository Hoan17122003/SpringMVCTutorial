package com.springmvc.tutorial.service.impl;

import com.springmvc.tutorial.model.entities.Category;
import com.springmvc.tutorial.model.repository.category.ICategoryRepository;
import com.springmvc.tutorial.service.ICategoryService;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(ICategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getPaginatedCategoriesOfPage(Integer page, int PAGE_SIZE, String searchValue) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE); // Tạo Pageable object
        List<Category> result = this.repository.findByCategoryName(searchValue, pageable);
        return result;
    }

    @Override
    public List<Category> getAllCategory() {
        return this.repository.findAll();
    }

    @Override
    public Long RowCount() {
        return repository.count();
    }

    @Override
    @Transactional
    public void updateCategory(Category category, int categoryID) {
//        this.repository.findById(categoryID).ifPresent(element -> {
////            var fields = element.getClass().getDeclaredFields();
////            for(var field : fields) {
////                field.setAccessible(true);
////                if(field.get(element) != category.)
////            }
////            Arrays.stream(fields).map(element -> {
////                return
////            }).collect(Collectors.toList());
//            element.setCategoryName(category.getCategoryName());
//            element.setDescription(category.getDescription());
//            this.repository.save(element);
//        });
        Optional<Category> existsCategory = this.repository.findById(categoryID);
        if (existsCategory.isPresent()) {
            Category category1 = existsCategory.get();
            category1.setDescription(category.getDescription());
            category1.setCategoryName(category.getCategoryName());
            System.out.println(new StringBuilder().append("category : ").append(category1));
            this.repository.save(category1);
        }
    }

    public void saveCategory(Category category) {
        System.out.println("saveCategoryService ...");
        if (category == null) return;

        this.repository.save(category);
    }

    @Override
    public Category findCategoryById(int id) {
        return this.repository.findById(id).orElse(new Category());
    }

    @Override
    public synchronized void deleteCategory(int id) {
        this.repository.deleteById(id);
    }
}
