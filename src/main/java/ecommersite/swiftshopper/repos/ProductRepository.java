package ecommersite.swiftshopper.repos;

import ecommersite.swiftshopper.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer>
{}