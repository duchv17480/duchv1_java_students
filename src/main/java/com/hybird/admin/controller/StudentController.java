package com.hybird.admin.controller;

import com.hybird.entities.Students;
import com.hybird.entities.Users;
import com.hybird.repository.StudentRepository;
import com.hybird.service.StudentService;
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
@RequestMapping("admin/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", studentService.findAll());
        return "views/students/list";
    }

    @GetMapping("/create")
    public String create(Students students) {
        return "views/students/create";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        studentService.delete(id);
        return "redirect:/admin/students";
    }

    @PostMapping("/store")
    public String store(@Valid Students students, BindingResult result) {
        if (result.hasErrors()){
            return "views/students/create";
        }
        studentService.create(students);
        return "redirect:/admin/students";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Students students = studentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("students", students);
        return "views/students/edit";
    }
    @PostMapping("/update/{id}")
    public String update(@Valid Students students, BindingResult result,@PathVariable("id")Integer id) {
        if (result.hasErrors()){
            students.setId(id);
            return "views/students/edit";
        }
        studentService.create(students);
        return "redirect:/admin/students";
    }
}
