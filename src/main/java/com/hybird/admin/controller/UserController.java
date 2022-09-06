package com.hybird.admin.controller;

import com.hybird.entities.Users;
import com.hybird.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    HttpServletRequest request;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", userRepository.findAll());
        return "views/users/list";
    }

    @GetMapping("/create")
    public String create(Users users) {
        return "views/users/create";
    }

    @PostMapping("/store")
    public String store(Model model, @Valid Users users, BindingResult result) {
        if (result.hasErrors()){
            return "views/users/create";
        }
        userRepository.save(users);
        return "redirect:/admin/users";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("users", user);
        return "views/users/edit";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @Valid Users users, BindingResult result,@PathVariable("id")String id) {
        if (result.hasErrors()){
            users.setUsername(id);
            return "views/users/edit";
        }
        userRepository.save(users);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }
    @PostMapping("changePassword")
    public String changePassword(Model model){
        String pass = request.getParameter("pass_new");
        String pass_confirm = request.getParameter("pass_confirm");
        String pass_old = request.getParameter("pass_old");
        if (!pass.trim().equals(pass_confirm.trim())){
            model.addAttribute("message","Pass confirm khong khop");
            return "views/users/changePass";
        }else {
            Users users = userRepository.findById(request.getRemoteUser()).get();
            if (users.getPassword().equals(pass_old.trim())){
                users.setPassword(pass);
                userRepository.save(users);
                return "redirect:/view";
            }else {
                model.addAttribute("message","Password sai roi");
                return "views/users/changePass";
            }
        }
    }
    @GetMapping("formChange")
    public String form(){
        return "views/users/changePass";
    }

}
