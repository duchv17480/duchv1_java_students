package com.hybrid.repositoryelaticseach;

import com.hybrid.entities.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
public interface StudentElasticSearchRepository extends ElasticsearchRepository<Student,Integer> {
    Iterable<Student> findStudentByLastName(String name);

}
