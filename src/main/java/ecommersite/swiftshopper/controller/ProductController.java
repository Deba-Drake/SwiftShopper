package ecommersite.swiftshopper.controller;

import ecommersite.swiftshopper.entites.Product;
import ecommersite.swiftshopper.exceptions.ProductNameNullException;
import ecommersite.swiftshopper.exceptions.ProductPriceNullException;
import ecommersite.swiftshopper.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController
{
    @Autowired
    ProductService productService;

    @GetMapping("/count-products")
    public long totalProducts()
    {
        return productService.getCountOfProducts();
    }

    @GetMapping("/all-products")
    public List<Product> allProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product Product(@PathVariable Integer id)
    {
        return productService.getProductById(id);
    }

    @PostMapping("/create-product")
    public Product createProduct(@RequestBody Product product)
    {
        if (product.getProductName() == null || product.getProductName().trim().isEmpty()|| product.getProductPrice() <= 0.00)
        {
            if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
                throw new ProductNameNullException("The Product Name cannot be Empty");
            }
            else if(product.getProductPrice() <= 0.00) {
                throw new ProductPriceNullException("The Product Price should be more than Zero");
            }
            else
            {
                throw new RuntimeException("Unknown validation error occurred");
            }
        }
        else
        {
            Product newProduct = product;
            productService.createProduct(newProduct);
            return newProduct;

            /*
            Product newProduct = productService.createProduct(product);
            productService.getProductById(newProduct.getProductID());

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newProduct.getProductID()).toUri();
            return ResponseEntity.created(location).build();
            */
        }
    }

    @DeleteMapping(path = "/product/{id}")
    public void deletesingleuser(@PathVariable Integer id)
    {
        productService.deleteProduct(id);
    }
}