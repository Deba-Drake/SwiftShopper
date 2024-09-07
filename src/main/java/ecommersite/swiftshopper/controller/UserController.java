package ecommersite.swiftshopper.controller;

import ecommersite.swiftshopper.entites.User;
import ecommersite.swiftshopper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController
{
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public User register(@RequestBody User user)
    {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user)
    {
        return userService.loginUser(user);
    }
}
