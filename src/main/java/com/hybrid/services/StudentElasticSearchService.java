package com.hybrid.services;

import com.hybrid.entities.Person;
import com.hybrid.entities.Student;
import com.hybrid.repositoryelaticseach.StudentElasticSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentElasticSearchService {
    private final StudentElasticSearchRepository searchRepository;

    @Autowired
    public StudentElasticSearchService(StudentElasticSearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public void save(Student student) {
        searchRepository.save(student);
    }

    public Student findById(Integer id) {
        return searchRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        searchRepository.deleteById(id);
    }

    public Iterable<Student> findAll() {
        return searchRepository.findAll();
    }

    public void update(Student student) {
        searchRepository.save(student);
    }

}
