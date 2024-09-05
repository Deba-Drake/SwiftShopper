package ecommersite.swiftshopper.controller;

import ecommersite.swiftshopper.entites.User;
import ecommersite.swiftshopper.repos.UserRepository;
import ecommersite.swiftshopper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public User register(@RequestBody User user)
    {
        return userService.registerUser(user);
    }
}
