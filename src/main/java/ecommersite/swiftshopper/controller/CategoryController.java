package ecommersite.swiftshopper.controller;

import ecommersite.swiftshopper.entites.Category;
import ecommersite.swiftshopper.exceptions.CategoryNameNullException;
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

    @PostMapping("/create-category")
    public Category createCategory(@RequestBody Category category)
    {
        if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) throw new CategoryNameNullException("The Category Name cannot be Empty");
        else if(category.getCategoryName() != null) return categoryService.createCategory(category);
        else throw new RuntimeException("Unknown validation error occurred");
    }

    @DeleteMapping(path = "/category/{id}")
    public String deleteCategory(@PathVariable Integer id)
    {
        return categoryService.deleteCategory(id);
    }

    @PutMapping(path = "/category/{id}")
    public Category updateCategory(@PathVariable Integer id, @RequestBody Category categoryDetails) {
        Category existingCategory = categoryService.getCategoryById(id).orElseThrow(() -> new CategoryNotFoundException("Category with ID " + id + " not found"));
        return categoryService.updateCategory(existingCategory, categoryDetails);
    }

    @GetMapping("/count-category")
    public long availableCategories()
    {
        return categoryService.getCountOfAvailableCategories();
    }
}