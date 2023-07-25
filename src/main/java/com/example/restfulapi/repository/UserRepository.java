package com.example.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restfulapi.models.UserInput;

@Repository
public interface UserRepository extends JpaRepository<UserInput, Long> {
    
}
