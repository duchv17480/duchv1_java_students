package com.hybrid.services;

import com.hybrid.entities.Student;
import com.hybrid.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }


    public Optional<Student> findById(Integer id) {
       return studentRepository.findById(id);
    }
}
