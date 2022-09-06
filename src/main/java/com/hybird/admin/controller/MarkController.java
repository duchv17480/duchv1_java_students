package com.hybird.admin.controller;

import com.hybird.entities.Marks;
import com.hybird.entities.Students;
import com.hybird.entities.Users;
import com.hybird.repository.MarksRepository;
import com.hybird.repository.StudentRepository;
import com.hybird.service.MarksService;
import com.hybird.service.StudentService;
import com.hybird.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/marks")
public class MarkController {

    @Autowired
    private MarksService marksService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MarksRepository marksRepository;

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public String list(Model model, @RequestParam("sid") Optional<Integer> sid){
        if (sid.isPresent()) {
            model.addAttribute("list",marksRepository.findMarks(sid.get()));
        }else {
            model.addAttribute("list",marksService.findAll());
        }
        return "/views/marks/list";
    }
    @GetMapping("create")
    private String create(Marks marks,Model model){
        model.addAttribute("listStudent",studentService.findAll());
        model.addAttribute("subjects",subjectService.findAll());
        return "/views/marks/create";
    }
    @PostMapping("/store")
    public String store(Marks marks) {
        marksService.save(marks);
        return "redirect:/admin/marks";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id")Integer id){
        marksService.delete(id);
        return "redirect:/admin/marks";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Marks marks, Model model) {
        model.addAttribute("listStudent",studentService.findAll());
        model.addAttribute("subjects",subjectService.findAll());
        model.addAttribute("marks", marks);
        return "views/marks/edit";
    }
    @PostMapping("/update/{id}")
    public String update(Marks marks) {
        marksService.save(marks);
        return "redirect:/admin/marks";
    }
}

