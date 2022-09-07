package com.hybird.service;

import com.hybird.entities.Marks;
import com.hybird.repositories.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarksService {
    @Autowired
    private MarksRepository marksRepository;

    public List<Marks> findAll(){
        return marksRepository.findAll();
    }

    public void save(Marks marks) {
        marksRepository.save(marks);
    }
    public void delete(Integer id){
        marksRepository.deleteById(id);
    }

    public Optional<Marks> findById(Integer id) {
        return marksRepository.findById(id);
    }
}
