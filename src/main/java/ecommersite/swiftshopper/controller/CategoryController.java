package ecommersite.swiftshopper.controller;

import ecommersite.swiftshopper.entites.Category;
import ecommersite.swiftshopper.exceptions.CategoryNotFoundException;
import ecommersite.swiftshopper.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController
{
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable Integer id)
    {
        return categoryService.getCategoryById(id).orElseThrow(() -> new CategoryNotFoundException("Category " + id + " not found."));
    }

    @GetMapping("/all-category")
    public List<Category> allCategories() {
        return categoryService.getAllCategories();
    }

}