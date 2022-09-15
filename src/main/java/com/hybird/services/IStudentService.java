package com.hybird.services;

import com.hybird.entities.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAll();
    Student create(Student student);
    void update (Student student);
    void delete(Integer id);
    Student getById(Integer id);
}
