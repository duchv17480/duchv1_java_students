package com.hybird.service;

import com.hybird.entities.Users;
import com.hybird.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users findById(String username) {
       return userRepository.findById(username).get();
    }
}
