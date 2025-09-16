package com.example.project.yamme.controller;
import com.example.project.yamme.model.Order;
import com.example.project.yamme.model.OrderItem;
import com.example.project.yamme.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return orderService.findById(id)
                .map(existing -> {
                    existing.setStatus(updatedOrder.getStatus());

                    // Atualiza os itens do pedido
                    if (updatedOrder.getItems() != null) {

                        existing.getItems().clear();
                        for (OrderItem item : updatedOrder.getItems()) {
                            item.setOrder(existing);

                            item.setSubtotal(item.getQuantity() * item.getUnitPrice());
                            existing.getItems().add(item);
                        }
                    }

                    return ResponseEntity.ok(orderService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderService.findById(id).isPresent()) {
            orderService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
