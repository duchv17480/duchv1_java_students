package com.hybird.rests;

import com.hybird.entities.Student;
import com.hybird.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<Iterable<Student>> getAllCategory() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Student student) {
        // convert DTO to entity
//        Students studentReq = modelMapper.map(studentDto, Students.class);
//        Students students = studentService.create(studentReq);
//        StudentDto studentRes = modelMapper.map(students, StudentDto.class);
//        return new ResponseEntity<>(studentRes, HttpStatus.CREATED);
        studentService.create(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
// viet tai lieu api input output , ma loi
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteCategory(@PathVariable("id") Integer id) {
        Optional<Student> studentsOptional = studentService.findById(id);
        return studentsOptional.map(students -> {
            studentService.delete(id);
            return new ResponseEntity<>(students, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
