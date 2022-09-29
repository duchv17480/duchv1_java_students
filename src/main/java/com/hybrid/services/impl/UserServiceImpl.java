package com.hybrid.services.impl;

import com.hybrid.entities.User;
import com.hybrid.repositories.UserRepository;
import com.hybrid.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void loginFromOAuth2(OAuth2AuthenticationToken token) {
        String email = token.getPrincipal().getAttribute("email");
        String password = Long.toHexString((System.currentTimeMillis()));
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(email).password(password).roles("US").build();
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    @Override
    public Optional<User> findByUsername(String s) {
        return userRepository.findByUsername(s);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
