package ecommersite.swiftshopper.service;

import ecommersite.swiftshopper.entites.User;
import ecommersite.swiftshopper.exceptions.UserNotFoundException;
import ecommersite.swiftshopper.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public User registerUser(User user)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String loginUser(User user)
    {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getFullName(), user.getPassword()));

        if (authentication.isAuthenticated()) return jwtService.generateToken(user.getFullName());
        else throw new UserNotFoundException("User Not Found");
    }
}
