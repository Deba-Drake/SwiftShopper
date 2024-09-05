package ecommersite.swiftshopper.service;

import ecommersite.swiftshopper.entites.User;
import ecommersite.swiftshopper.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public User registerUser(User user)
    {
        User userS1=user;
        userS1.setPassword(bCryptPasswordEncoder.encode(userS1.getPassword()));
        return userRepository.save(userS1);
    }
}
