package com.hybrid.repositoryelaticseach;

import com.hybrid.entities.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentElasticSearchRepository extends ElasticsearchRepository<Student,Integer> {
//    @Query("SELECT s from Student s where s.firstName=?1 or s.address=?2 or s.phone=?3")
//    Iterable<Student> find (String name, String address, String phone);
    Iterable<Student> findStudentByLastNameLikeIgnoreCaseOrAddressLikeIgnoreCaseOrPhoneLike(String name,String address,String phone);

}
