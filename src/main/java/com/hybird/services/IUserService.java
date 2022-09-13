package com.hybird.services;

import com.hybird.entities.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String s);
    Boolean existsByUsername(String username);
    User save(User user);
}
