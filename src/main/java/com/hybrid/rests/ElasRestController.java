package com.hybrid.rests;

import com.hybrid.entities.Person;
import com.hybrid.services.ElasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/person")
public class ElasRestController {
    private final ElasService elasService;

    @Autowired
    public ElasRestController(ElasService elasService) {
        this.elasService = elasService;
    }

    @PostMapping
    public void save(@RequestBody Person person) {
        elasService.save(person);
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable("id") Integer id) {
        return elasService.findById(id);
    }
}
