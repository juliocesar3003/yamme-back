package com.example.project.yamme.repository;

import com.example.project.yamme.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
