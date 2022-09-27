package com.hybrid.services;

import com.hybrid.entities.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String s);
    Boolean existsByUsername(String username);
    User save(User user);
}
