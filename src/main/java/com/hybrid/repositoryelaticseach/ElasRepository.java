package com.hybrid.repositoryelaticseach;

import com.hybrid.entities.Person;
import com.hybrid.entities.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasRepository extends ElasticsearchRepository<Person, Integer> {
}
