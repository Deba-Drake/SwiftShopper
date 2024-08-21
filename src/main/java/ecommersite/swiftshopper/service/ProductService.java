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

    //Method to get a In-Stock Product by its ID
    public String findInStockProducts(Integer id)
    {
        Product currentProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        if(currentProduct.getProductStockQuantity() > 0) return "The Product with ID: "+id+"is In-Stock with Quantity"+currentProduct.getProductStockQuantity();
        else return "The Product with ID: "+id+"is Not In-Stock";
    }

    //Method to get the Products in a Price Range
    public List<Product> findProductsByPriceRange(double minPrice, double maxPrice)
    {
        return productRepository.findProductsBetweenPrice(minPrice, maxPrice);
    }

    //Method to get all Products
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    //Method to get a Product by its ID
    public Product getProductById(Integer id)
    {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
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

    //Method to update a Product if exist
    public Product updateProduct(Product existingProduct, Product productDetails)
    {
        existingProduct.setProductName(productDetails.getProductName());
        existingProduct.setProductPrice(productDetails.getProductPrice());
        existingProduct.setProductStockQuantity(productDetails.getProductStockQuantity());
        existingProduct.setProductIsRefundable(productDetails.isProductIsRefundable());
        existingProduct.setProductIsReturnable(productDetails.isProductIsReturnable());
        existingProduct.setProductIsAvailable(productDetails.isProductIsAvailable());
        return productRepository.save(existingProduct);
    }
}