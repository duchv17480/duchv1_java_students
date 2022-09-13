package com.hybird.services;

import com.hybird.entities.User;
import com.hybird.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    User findByUsername(String s){
        return userRepository.findName(s);
    }
}
