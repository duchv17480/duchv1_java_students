package com.hybrid.services.impl;

import com.hybrid.entities.User;
import com.hybrid.repositories.UserRepository;
import com.hybrid.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceimpl implements IUserService {

    @Autowired
    UserRepository userRepository;

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
