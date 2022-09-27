package com.hybrid.repositories;

import com.hybrid.entities.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Marks,Integer> {
    @Query("SELECT m from Marks m where m.subjectId=?1")
    List<Marks> findMarks(int sid);
}
