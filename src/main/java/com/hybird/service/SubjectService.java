package com.hybird.service;

import com.hybird.entities.Subjects;
import com.hybird.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subjects> findAll(){
        return subjectRepository.findAll();
    }

    public void save(Subjects subjects) {
        subjectRepository.save(subjects);
    }
    public void delete(Integer id){
        subjectRepository.deleteById(id);
    }
}
