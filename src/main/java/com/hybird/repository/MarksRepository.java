package com.hybird.repository;

import com.hybird.entities.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks,Integer> {
    @Query("SELECT m from Marks m where m.subjectsId.id=?1")
    List<Marks> findMarks(int sid);
}
