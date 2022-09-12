package com.hybird.services;

import com.hybird.entities.Marks;
import com.hybird.repositories.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarkService {
    @Autowired
    private MarkRepository markRepository;

    public List<Marks> findAll(){
        return markRepository.findAll();
    }

    public void save(Marks marks) {
        markRepository.save(marks);
    }
    public void delete(Integer id){
        markRepository.deleteById(id);
    }

    public Optional<Marks> findById(Integer id) {
        return markRepository.findById(id);
    }
}
