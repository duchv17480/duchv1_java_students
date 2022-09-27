package com.hybrid.controllers.admins;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/security/login/form")
    public String loginForm(Model model) {
        model.addAttribute("messageLoi", "Vui lòng đăng nhập!");
        return "views/layout/login";
    }

    @RequestMapping("/security/login/success")
    public String loginSuccess(Model model) {
        model.addAttribute("message", "Đăng nhập thành công!");
        return "views/layout/login";
    }

    @RequestMapping("/security/login/error")
    public String loginError(Model model) {
        model.addAttribute("messageLoi", "Sai thông tin đăng nhập!");
        return "views/layout/login";
    }

    @RequestMapping("/security/unauthorized")
    public String unauthoried(Model model) {
        model.addAttribute("messageLoi", "Không có quyền truy xuất!");
        return "views/layout/login";
    }

    @RequestMapping("/security/logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("message", "Bạn đã đăng xuất!");
        return "views/layout/login";
    }
}

