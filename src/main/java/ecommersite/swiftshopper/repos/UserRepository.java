package ecommersite.swiftshopper.repos;

import ecommersite.swiftshopper.entites.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer>
{
    @Query("SELECT u FROM User u WHERE CONCAT(u.firstName, ' ', u.lastName) = :fullName")
    User findByFullName(@Param("fullName") String fullName);
}