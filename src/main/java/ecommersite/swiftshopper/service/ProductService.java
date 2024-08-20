package ecommersite.swiftshopper.service;

import ecommersite.swiftshopper.entites.Product;
import ecommersite.swiftshopper.exceptions.ProductNotFoundException;
import ecommersite.swiftshopper.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;

    //Method to get Total Number of Products present in the table
    public long getCountOfAvailableProducts()
    {
        return productRepository.availableProducts();
    }

    //Method to get all Products
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    //Method to get a Product by its ID
    public Product getProductById(Integer id)
    {
        return productRepository.findById(id).orElse(null);
    }

    //Method to get a Product by its ID
    public List<Product> findProductsByCategory(String category ) {
        return productRepository.productsInCategory(category);
    }

    //Method to add a new Product
    public Product createProduct(Product product)
    {
        return productRepository.save(product);
    }

    //Method to delete a Product if exist
    public void deleteProduct(Integer id)
    {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setProductIsAvailable(false);
        productRepository.save(product);
    }
}