package ecommersite.swiftshopper.service;

import ecommersite.swiftshopper.entites.Product;
import ecommersite.swiftshopper.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(Integer id)
    {
        Product temp = productRepository.findById(id).orElse(null);
        return temp;
    }
}