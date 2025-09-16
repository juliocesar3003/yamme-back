package com.example.project.yamme.repository;

import com.example.project.yamme.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
