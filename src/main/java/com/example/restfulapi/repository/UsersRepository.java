package com.example.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restfulapi.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    
}
