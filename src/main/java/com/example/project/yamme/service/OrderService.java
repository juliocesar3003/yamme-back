package com.example.project.yamme.service;
import com.example.project.yamme.model.Order;
import com.example.project.yamme.model.OrderItem;
import com.example.project.yamme.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService{

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    public Order save(Order order) {
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                item.setOrder(order);
                item.setSubtotal(item.getQuantity() * item.getUnitPrice());
            }
        } else {
            order.setItems(new ArrayList<>());
        }
        return repository.save(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
