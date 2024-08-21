package ecommersite.swiftshopper.repos;

import ecommersite.swiftshopper.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer>
{
    @Query("SELECT COUNT(p) FROM Product p WHERE p.productIsAvailable = true")
    long availableProducts();

    @Query(value = "SELECT * FROM product WHERE Category = ?1", nativeQuery = true)
    List<Product> productsInCategory(String category);

    @Query(value = "SELECT * FROM product WHERE Price BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Product> findProductsBetweenPrice(double minPrice, double maxPrice);
}