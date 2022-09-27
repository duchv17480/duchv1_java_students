package com.hybrid.repositories;

import com.hybrid.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    @Query("select u from User u where u.username=:username")
    User findName(String username);
    Optional<User> findByUsername(String s);
    Boolean existsByUsername(String username);

}
