package com.hybrid.services;

import com.hybrid.entities.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Optional;

public interface IUserService {
    public void loginFromOAuth2(OAuth2AuthenticationToken token);
    Optional<User> findByUsername(String s);
    Boolean existsByUsername(String username);
    User save(User user);
}
