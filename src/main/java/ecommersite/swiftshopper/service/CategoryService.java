package ecommersite.swiftshopper.service;

import ecommersite.swiftshopper.entites.Category;
import ecommersite.swiftshopper.entites.Product;
import ecommersite.swiftshopper.exceptions.CategoryNotFoundException;
import ecommersite.swiftshopper.exceptions.ProductNotFoundException;
import ecommersite.swiftshopper.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    //Method to get all Products
    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    //Method to get a Category by its ID
    @Cacheable(value = "categories", key = "#id")
    public Optional<Category> getCategoryById(Integer id)
    {
        return categoryRepository.findById(id);
    }

    //Method to create a Category
    @CacheEvict(value = "categories", allEntries = true)
    public Category createCategory(Category category)
    {
        return categoryRepository.save(category);
    }

    //Method to delete a Category if it exists
    @CacheEvict(value = "categories", allEntries = true)
    public String deleteCategory(Integer id)
    {
        Optional<Category> currentCategoryOptional = getCategoryById(id);

        if(currentCategoryOptional.isPresent())
        {
            Category category = currentCategoryOptional.get();
            category.setCategoryIsAvailable(false);
            categoryRepository.save(category);
            return "Category with ID: " + id + " has been marked as deleted.";
        }
        else throw new CategoryNotFoundException("The Category with ID: " + id + " was not found");
    }
}