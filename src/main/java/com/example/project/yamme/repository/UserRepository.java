package com.example.project.yamme.repository;

import com.example.project.yamme.model.Category;
import com.example.project.yamme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
