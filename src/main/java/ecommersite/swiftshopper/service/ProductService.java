package ecommersite.swiftshopper.service;

import ecommersite.swiftshopper.entites.Product;
import ecommersite.swiftshopper.exceptions.ProductNotFoundException;
import ecommersite.swiftshopper.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;

    //Method to get Total Number of Products present in the table
    public long getCountOfAvailableProducts() {return productRepository.availableProducts();}

    //Method to check by ID that if a Product is In-Stock
    @Cacheable(value = "products", key = "#id")
    public String findInStockProducts(Integer id) {return productRepository.findById(id).map(product -> product.getProductStockQuantity() > 0 ? "The Product with ID: " + id + " is In-Stock with Quantity: " + product.getProductStockQuantity() : "The Product with ID: " + id + " is Not In-Stock").orElseThrow(() -> new ProductNotFoundException("Product not found"));}

    //Method to get the Products in a Price Range
    public List<Product> findProductsByPriceRange(double minPrice, double maxPrice) {return Optional.ofNullable(productRepository.findProductsBetweenPrice(minPrice, maxPrice)).orElse(Collections.emptyList());}

    //Method to get a Product by its ID
    //@Cacheable(value = "productsInCategory")
    public List<Product> findProductsByCategory(String category) {return Optional.ofNullable(productRepository.productsInCategory(category)).orElse(Collections.emptyList());}

    //Method to get all Products
    public List<Product> getAllProducts() {return productRepository.findAll();}

    //Method to get a Product by its ID
    //@Cacheable(value = "products", key = "#id")
    public Optional<Product> getProductById(Integer id) {return productRepository.findById(id);}

    //Method to create a Product
    //@CacheEvict(value = "allProducts", allEntries = true)
    public Product createProduct(Product product) {return productRepository.save(product);}

    //Method to delete a Product if it exists
    //@CacheEvict(value = "products", key = "#id")
    public String deleteProduct(Integer id) {
        Optional<Product> currentProductOptional = getProductById(id);

        if(currentProductOptional.isPresent())
        {
            Product product = currentProductOptional.get();
            product.setProductIsAvailable(false);
            productRepository.save(product);
            return "Product with ID: " + id + " has been marked as deleted.";
        }
        else throw new ProductNotFoundException("The Product with ID: " + id + " was not found");
    }

    //Method to update a Product if it exists
    //@CacheEvict(value = "products", key = "#product.id")
    public Product updateProduct(Product existingProduct, Product productDetails) {
        existingProduct.setProductName(productDetails.getProductName());
        existingProduct.setProductPrice(productDetails.getProductPrice());
        existingProduct.setProductStockQuantity(productDetails.getProductStockQuantity());
        existingProduct.setProductIsRefundable(productDetails.isProductIsRefundable());
        existingProduct.setProductIsReturnable(productDetails.isProductIsReturnable());
        existingProduct.setProductIsAvailable(productDetails.isProductIsAvailable());
        return productRepository.save(existingProduct);
    }
}