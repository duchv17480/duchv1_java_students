package com.hybrid.rests;

import com.hybrid.dtos.UserDTO;
import com.hybrid.entities.User;
import com.hybrid.repositories.UserRepository;
import com.hybrid.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("change")
    public @ResponseBody Response change(@RequestBody UserDTO userDTO) {
        if (!userDTO.getPassNew().equals(userDTO.getPassConfirm())) {
            return Response.error("pass comfirm khong khop");
        } else {
            User user = userRepository.findName(userDTO.getUsername());
            if (!user.getPassword().equals(userDTO.getPassOld())) {
                return Response.error("looix");
            } else {
                user.setPassword(userDTO.getPassNew());
                User user2 = userRepository.save(user);
                return Response.success("ok").withData(user2);
            }
        }
    }
}
