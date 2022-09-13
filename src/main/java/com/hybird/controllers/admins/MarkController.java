package com.hybird.controllers.admins;

import com.hybird.entities.Marks;
import com.hybird.repositories.MarkRepository;
import com.hybird.services.MarkService;
import com.hybird.services.StudentService;
import com.hybird.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("admin/marks")
public class MarkController {

    @Autowired
    private MarkService markService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public String list(Model model, @RequestParam("sid") Optional<Integer> sid){
        if (sid.isPresent()) {
            model.addAttribute("list", markRepository.findMarks(sid.get()));
        }else {
            model.addAttribute("list", markService.findAll());
        }
        return "/views/marks/list";
    }
    @GetMapping("create")
    private String create(Marks marks,Model model){
        model.addAttribute("listStudent",studentService.findAll());
        model.addAttribute("subject",subjectService.findAll());
        return "/views/marks/create";
    }
    @PostMapping("/store")
    public String store(Marks marks) {
        markService.save(marks);
        return "redirect:/admin/marks";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id")Integer id){
        markService.delete(id);
        return "redirect:/admin/marks";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Marks marks, Model model) {
        model.addAttribute("listStudent",studentService.findAll());
        model.addAttribute("subject",subjectService.findAll());
        model.addAttribute("marks", marks);
        return "views/marks/edit";
    }
    @PostMapping("/update/{id}")
    public String update(Marks marks) {
        markService.save(marks);
        return "redirect:/admin/marks";
    }
}

