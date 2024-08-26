package ecommersite.swiftshopper.service;

import ecommersite.swiftshopper.entites.Category;
import ecommersite.swiftshopper.entites.Product;
import ecommersite.swiftshopper.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    //Method to get a Product by its ID
    @Cacheable(value = "categories", key = "#id")
    public Optional<Category> getCategoryById(Integer id)
    {
        return categoryRepository.findById(id);
    }
}