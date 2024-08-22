package ecommersite.swiftshopper.service;

import ecommersite.swiftshopper.entites.Product;
import ecommersite.swiftshopper.exceptions.ProductNotFoundException;
import ecommersite.swiftshopper.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<String> findInStockProducts(Integer id) {return productRepository.findById(id).map(product -> product.getProductStockQuantity() > 0 ? Optional.of("The Product with ID: " + id + " is In-Stock with Quantity: " + product.getProductStockQuantity()) : Optional.of("The Product with ID: " + id + " is Not In-Stock")).orElseThrow(() -> new ProductNotFoundException("Product not found"));}

    //Method to get the Products in a Price Range
    public Optional<List<Product>> findProductsByPriceRange(double minPrice, double maxPrice) {return Optional.ofNullable(productRepository.findProductsBetweenPrice(minPrice, maxPrice));}

    //Method to get a Product by its ID
    public Optional<List<Product>> findProductsByCategory(String category ) {return Optional.ofNullable(productRepository.productsInCategory(category));}

    //Method to get all Products
    public List<Product> getAllProducts() {return productRepository.findAll();}

    //Method to get a Product by its ID
    public Optional<Product> getProductById(Integer id) {return productRepository.findById(id);}

    //Method to add a new Product
    public Product createProduct(Product product) {return productRepository.save(product);}

    //Method to delete a Product if exist
    public String deleteProduct(Integer id) {
        Optional<Product> currentProductOptional = getProductById(id);

//        if (currentProductOptional.isPresent())
//        {
//            Product currentProduct = currentProductOptional.get();
//            currentProduct.setProductIsAvailable(false);
//            productRepository.save(currentProduct); // Update the product's availability
//            return "Product with ID: " + id + " has been marked as deleted.";
//        }
//        else throw new ProductNotFoundException("Product with ID: " + id + " not found.");

        Product currentProduct = currentProductOptional.get();
        currentProduct.setProductIsAvailable(false);
        productRepository.save(currentProduct); // Update the product's availability
        return "Product with ID: " + id + " has been marked as deleted.";
    }

    //Method to update a Product if exist
    public Product updateProduct(Optional<Product> existingProduct, Product productDetails)
    {
        Product updateProduct = existingProduct.get();
        updateProduct.setProductName(productDetails.getProductName());
        updateProduct.setProductPrice(productDetails.getProductPrice());
        updateProduct.setProductStockQuantity(productDetails.getProductStockQuantity());
        updateProduct.setProductIsRefundable(productDetails.isProductIsRefundable());
        updateProduct.setProductIsReturnable(productDetails.isProductIsReturnable());
        updateProduct.setProductIsAvailable(productDetails.isProductIsAvailable());
        return productRepository.save(updateProduct);
    }
}