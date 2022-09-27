package com.hybrid.services;

import com.hybrid.entities.User;
import com.hybrid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    User findByUsername(String s){
        return userRepository.findName(s);
    }
}
