package ecommersite.swiftshopper.controller;

import ecommersite.swiftshopper.entites.Product;
import ecommersite.swiftshopper.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController
{
    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Integer id)
    {
        return productService.getProductById(id);
    }
}