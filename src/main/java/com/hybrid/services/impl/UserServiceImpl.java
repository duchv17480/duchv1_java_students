package com.hybrid.services.impl;

import com.hybrid.entities.User;
import com.hybrid.repositories.UserRepository;
import com.hybrid.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
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
    public User save(User user,String storeLocation) {
        if(user == null) return null;
        try {
            if(!user.getFile().isEmpty()) {
                user.setPhoto(saveImageAndGetLocation(user.getFile(), storeLocation));
            }

            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error is : {}", e.getMessage(), e);
            return null;
        }
        return user;
    }
    private String saveImageAndGetLocation(MultipartFile file, String storeLocation) {
        if(file.isEmpty()) return null;

        String fileName = generateImageName(file.getOriginalFilename());
        if(fileName == null) return null;

        try {
            // Create a directory if not exist
            File dir = new File(storeLocation);
            if(!dir.exists()) dir.mkdirs();

            // Upload file into server location
            Files.copy(file.getInputStream(), Paths.get(storeLocation, fileName));
        } catch (IOException e) {
            log.error("Error is : {}", e.getMessage(), e);
            return null;
        }
        return fileName;
    }

    private String generateImageName(String originalFileName) {
        if(originalFileName == null || originalFileName.isEmpty()) return null;

        String extension = null;
        int j = originalFileName.lastIndexOf('.');
        if (j > 0) {
            extension = originalFileName.substring(j + 1);
        }
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String fileName = UUID.randomUUID() +  timeStamp + "." + extension;
        log.debug("File name is now: {}", fileName);
        return fileName;
    }
}
