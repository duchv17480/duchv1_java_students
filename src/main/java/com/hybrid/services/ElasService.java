package com.hybrid.services;

import com.hybrid.entities.Person;
import com.hybrid.repositoryelaticseach.ElasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElasService {
    private final ElasRepository elasRepository;

    @Autowired
    public ElasService(ElasRepository elasRepository) {
        this.elasRepository = elasRepository;
    }

    public void save(Person person) {
        elasRepository.save(person);
    }

    public Person findById(Integer id) {
        return elasRepository.findById(id).orElse(null);
    }
}
