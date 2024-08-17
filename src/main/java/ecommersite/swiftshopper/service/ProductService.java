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
    public long getCountOfProducts()
    {
        return productRepository.count();
    }

    //Method to get all Products
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    //Method to get a Product by its ID
    public Product getProductById(Integer id)
    {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
    }

    //Method to add a new Product
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    //Method to delete a Product if exist
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productRepository.delete(product);
    }

    /*
    public Product updateProduct(Integer productId, Product productDetails) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        existingProduct.setProductName(productDetails.getProductName());
        existingProduct.setProductDescription(productDetails.getProductDescription());
        // Set other fields as needed...

        return productRepository.save(existingProduct);
    }


*/
}