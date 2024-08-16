package ecommersite.swiftshopper.controller;

import ecommersite.swiftshopper.entites.Category;
import ecommersite.swiftshopper.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController
{
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable Integer id)
    {
        return categoryService.getCategoryById(id);
    }
}