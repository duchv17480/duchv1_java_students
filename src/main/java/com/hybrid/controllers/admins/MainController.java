package com.hybrid.controllers.admins;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("view")
    public String view(){
        return "views/layout/_maincontent";
    }
}
