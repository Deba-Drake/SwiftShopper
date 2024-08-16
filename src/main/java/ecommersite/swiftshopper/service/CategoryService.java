package ecommersite.swiftshopper.service;

import ecommersite.swiftshopper.entites.Category;
import ecommersite.swiftshopper.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryById(Integer id)
    {
        return categoryRepository.findById(id).orElse(null);
    }
}