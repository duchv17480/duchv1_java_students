package com.hybird.services.impl;

import com.hybird.entities.Student;
import com.hybird.repositories.StudentRepository;
import com.hybird.services.IStudentService;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceimpl implements IStudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void update(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student getById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }
}