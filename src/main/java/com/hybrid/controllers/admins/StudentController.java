package com.hybrid.controllers.admins;

import com.hybrid.entities.Student;
import com.hybrid.services.StudentService;
import com.hybrid.utils.UpLoadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("admin/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UpLoadFileUtils utils;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", studentService.findAll());
        return "views/students/list";
    }

    @GetMapping("/create")
    public String create(Student student) {
        return "views/students/create";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        studentService.delete(id);
        return "redirect:/admin/students";
    }

    @PostMapping("/store")
    public String store(Student student, @RequestParam("imageFile")MultipartFile multipartFile) throws IOException {
        student.setPhoto(multipartFile.getOriginalFilename());
        this.utils.handleUploadFile(multipartFile);
        studentService.create(student);
        return "redirect:/admin/students";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Student student = studentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("students", student);
        return "views/students/edit";
    }
    @PostMapping("/update/{id}")
    public String update(Student student, BindingResult result, @PathVariable("id")Integer id) {
        if (result.hasErrors()){
            student.setId(id);
            return "views/students/edit";
        }
        studentService.create(student);
        return "redirect:/admin/students";
    }
}
