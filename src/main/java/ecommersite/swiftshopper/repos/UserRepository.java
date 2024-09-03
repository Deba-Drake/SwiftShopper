package ecommersite.swiftshopper.repos;

import ecommersite.swiftshopper.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByFullName(String username);
}