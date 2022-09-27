package com.hybrid.security.userpincal;

import com.hybrid.entities.User;
import com.hybrid.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found -> user name or password"+ username));
        return UserPrinciple.build(user);
    }
}
