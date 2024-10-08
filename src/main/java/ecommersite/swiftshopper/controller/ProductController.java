package ecommersite.swiftshopper.controller;

import ecommersite.swiftshopper.entites.Product;
import ecommersite.swiftshopper.exceptions.ProductNameNullException;
import ecommersite.swiftshopper.exceptions.ProductNotFoundException;
import ecommersite.swiftshopper.exceptions.ProductPriceNullException;
import ecommersite.swiftshopper.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ProductController
{
    @Autowired
    ProductService productService;

    @GetMapping("/csrf_token")
    public CsrfToken getCRSFToken(HttpServletRequest request)
    {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/count-product")
    public long availableProducts()
    {
        return productService.getCountOfAvailableProducts();
    }

    @GetMapping("/instock-product/{id}")
    public String inStockProducts(@PathVariable Integer id) {return productService.findInStockProducts(id);}

    @GetMapping("/product-priceRange/{range}")
    public List<Product> getProductsByPriceRange(@PathVariable String range)
    {
        String[] prices = range.split(",");
        double minPrice = Double.parseDouble(prices[0]);
        double maxPrice = Double.parseDouble(prices[1]);
        return productService.findProductsByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/count-product-category/{category}")
    public List<Product> productsInCategory(@PathVariable String category) {return productService.findProductsByCategory(category);}

    @GetMapping("/all-products")
    public List<Product> allProducts() {return productService.getAllProducts();}

    @GetMapping("/product/{id}")
    public Product singleProduct(@PathVariable Integer id) {return productService.getProductById(id).orElseThrow(() -> new ProductNotFoundException("Product " + id + " not found."));}

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
    public String deleteProduct(@PathVariable Integer id) {return productService.deleteProduct(id);}

    @PutMapping(path = "/product/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product productDetails)
    {
        Product existingProduct = productService.getProductById(id).orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        return productService.updateProduct(existingProduct, productDetails);
    }

}