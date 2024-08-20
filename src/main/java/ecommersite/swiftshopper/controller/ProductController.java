package ecommersite.swiftshopper.controller;

import ecommersite.swiftshopper.entites.Product;
import ecommersite.swiftshopper.exceptions.ProductNameNullException;
import ecommersite.swiftshopper.exceptions.ProductNotFoundException;
import ecommersite.swiftshopper.exceptions.ProductPriceNullException;
import ecommersite.swiftshopper.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController
{
    @Autowired
    ProductService productService;

    @GetMapping("/count-product")
    public long availableProducts() {
        return productService.getCountOfAvailableProducts();
    }

    @GetMapping("/count-product-category/{category}")
    public List<Product> productsInCategory(@PathVariable String category) {
        return productService.findProductsByCategory(category);
    }

    @GetMapping("/all-products")
    public List<Product> allProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product sinlgeProduct(@PathVariable Integer id)
    {
        if (productService.getProductById(id)==null)
        {
            throw new ProductNotFoundException("Product " + id + " not found.");
        }
        else return productService.getProductById(id);
    }

    @PostMapping("/create-product")
    public Product createProduct(@RequestBody Product product)
    {
        if (product.getProductName() == null || product.getProductName().trim().isEmpty()|| product.getProductPrice() <= 0.00)
        {
            if (product.getProductName() == null || product.getProductName().trim().isEmpty()) throw new ProductNameNullException("The Product Name cannot be Empty");
            else if(product.getProductPrice() <= 0.00) throw new ProductPriceNullException("The Product Price should be more than Zero");
            else throw new RuntimeException("Unknown validation error occurred");
        }
        else return productService.createProduct(product);
    }

    @DeleteMapping(path = "/product/{id}")
    public void deleteProduct(@PathVariable Integer id)
    {
        productService.deleteProduct(id);
    }
}