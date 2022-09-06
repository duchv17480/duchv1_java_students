package com.hybird.service;

import com.hybird.entities.Students;
import com.hybird.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Students> findAll() {
        return studentRepository.findAll();
    }

    public Students create(Students students) {
        return studentRepository.save(students);
    }

    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }


    public Optional<Students> findById(Integer id) {
       return studentRepository.findById(id);
    }
}
