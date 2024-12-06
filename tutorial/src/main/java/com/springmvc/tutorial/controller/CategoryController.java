package com.springmvc.tutorial.controller;

import com.springmvc.tutorial.model.entities.Category;
import com.springmvc.tutorial.service.ICategoryService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/Category")
public class CategoryController {

    private final ICategoryService categoryService;
    private static int PAGE_SIZE = 5;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/login")
    public String Login(ModelMap modelMap) {
        return "auth/login";
    }

    @PostMapping("/login")
    public String LoginPost() {
        return "redirect:/";
    }

    @GetMapping
    public String Index(ModelMap modelMap,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "") String searchValue) {

        List<Category> categories = categoryService.getPaginatedCategoriesOfPage(page, PAGE_SIZE, searchValue);
        Long rowCount = this.categoryService.RowCount();
        Double count = (double) rowCount / PAGE_SIZE;
        int pageCount = (int) Math.ceil(count);
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("pageCount", pageCount);
        modelMap.addAttribute("rowCount", rowCount);
        modelMap.addAttribute("searchValue", searchValue);
        return "Category/Index";
    }

    @GetMapping(path = "/Edit/{id}")
    public String Edit(@PathVariable("id") int id, ModelMap modelMap) {
        var category = this.categoryService.findCategoryById(id);
        modelMap.addAttribute("id", id);
        modelMap.addAttribute("category", category);
        return "Category/Edit";
    }

    @GetMapping(path = "/Create")
    public String Save(ModelMap modelMap) {
        Category category = new Category();
        modelMap.addAttribute("category", category);
        return "Category/Edit";
    }

    @PostMapping(path = "/Save"
    // , consumes = {"application/json"}
    )
    // use @ModelAttribute annotation when thymeleaf because it's use mvc
    // achitecture and use @RequestBody annotation when RestfullAPI
    public String SaveCategory(@ModelAttribute Category category, @RequestParam("categoryId") Integer categoryId) {

        if (categoryId == 0) {
            this.categoryService.saveCategory(category);
        } else {
            this.categoryService.updateCategory(category, categoryId);
        }
        return "redirect:/Category";
    }

    @PostMapping("/Delete/{id}")
    public String DeleteCategory(@PathVariable("id") Integer id) {
        this.categoryService.deleteCategory(id);
        return "redirect:/Category";
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity getAllCategory(@RequestParam(value = "SearchValue") String searchValue) {
        var categories = this.categoryService.getPaginatedCategoriesOfPage(1, PAGE_SIZE, searchValue);
        return ResponseEntity.ok(categories);
    }
}
