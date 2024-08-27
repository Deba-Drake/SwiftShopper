package ecommersite.swiftshopper.repos;

import ecommersite.swiftshopper.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category,Integer>
{
    @Query("SELECT COUNT(c) FROM Category c WHERE c.categoryIsAvailable = true")
    long availableCategories();
}