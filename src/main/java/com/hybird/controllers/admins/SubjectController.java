package com.hybird.controllers.admins;

import com.hybird.entities.Subjects;
import com.hybird.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("admin/subjects")
public class SubjectController {
    @Autowired
    private SubjectService service;
    @GetMapping
    public String list(Model model){
        model.addAttribute("list",service.findAll());
        return "views/subjects/list";
    }
    @GetMapping("/create")
    public String create(Subjects subjects) {
        return "views/subjects/create";
    }
    @PostMapping("/store")
    public String store(@Valid Subjects subjects, BindingResult result) {
        if(result.hasErrors()){
            return "views/subjects/create";
        }
        service.save(subjects);
        return "redirect:/admin/subjects";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id")Integer id){
        service.delete(id);
        return "redirect:/admin/subjects";
    }
}

