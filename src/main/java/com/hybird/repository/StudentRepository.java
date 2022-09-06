package com.hybird.repository;

import com.hybird.entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students,Integer> {
}
