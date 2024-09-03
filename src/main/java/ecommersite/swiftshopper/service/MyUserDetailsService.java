package ecommersite.swiftshopper.service;

import ecommersite.swiftshopper.entites.MyUserDetail;
import ecommersite.swiftshopper.entites.User;
import ecommersite.swiftshopper.exceptions.UserNotFoundException;
import ecommersite.swiftshopper.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByFullName(username);

        if(user == null)
        {
            throw new UserNotFoundException("User not Found");
        }
        else return new MyUserDetail(user);
    }
}
