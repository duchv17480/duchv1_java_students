package com.hybird.rests;

import com.hybird.dtos.request.LoginRequest;
import com.hybird.dtos.response.LoginResponse;
import com.hybird.entities.Role;
import com.hybird.entities.User;
import com.hybird.repositories.RoleRepository;
import com.hybird.security.jwt.JwtProvider;
import com.hybird.security.userpincal.UserPrinciple;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.hybird.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AuthenRestController {
    @Autowired
    IUserService userService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public LoginResponse login (@RequestBody LoginRequest loginRequest){
        // Xác thực thông tin người dùng Request lên
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        // Nếu k xảy ra exception tức là thong tin hợp lệ
        // Set thong tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Trả về jwt cho người dùng.
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new LoginResponse(token,userPrinciple.getFullName(),userPrinciple.getAuthorities())).getBody();
    }

//    @PostMapping("/singUp")
//    public ResponseEntity<?> register(@Valid @RequestBody LoginRequest loginRequest){
//        if (userService.existsByUsername(loginRequest.getUsername())){
//            return new ResponseEntity<>(new LoginResponse("The Username is existed"), HttpStatus.OK);
//        }
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        passwordEncoder.encode(loginRequest.getPassword())
//                )
//        );
//        Set<Role> roles = new HashSet<>();
//        Set<String> stringRole = loginRequest.getRole();
//        stringRole.forEach(role ->{
//            switch (role){
//                case "AD":
//                    Role admin = roleRepository.findById(0).orElseThrow( ()-> new RuntimeException("Role not found"));
//                    roles.add(admin);
//                    break;
//                case "US":
//                    Role user = roleRepository.findById(1).orElseThrow( ()-> new RuntimeException("Role not found"));
//                    roles.add(user);
//                    break;
//            }
//        });
//    }

}
