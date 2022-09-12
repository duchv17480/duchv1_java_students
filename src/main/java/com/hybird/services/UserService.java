package com.hybird.services;

import com.hybird.entities.User;
import com.hybird.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(String username) {
       return userRepository.findById(username).get();
    }
}
