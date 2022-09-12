package com.hybird.services;

import com.hybird.entities.Subject;
import com.hybird.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

    public void save(Subject subject) {
        subjectRepository.save(subject);
    }
    public void delete(Integer id){
        subjectRepository.deleteById(id);
    }
}
