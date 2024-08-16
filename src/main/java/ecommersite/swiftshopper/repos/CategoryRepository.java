package ecommersite.swiftshopper.repos;

import ecommersite.swiftshopper.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer>
{}