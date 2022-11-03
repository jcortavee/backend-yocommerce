package com.seminario.store.controllers;

import com.seminario.store.models.Order;
import com.seminario.store.servicies.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        var orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") Long id) {
        var order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) {
        var orderSaved = orderService.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@RequestBody Order order, @PathVariable("id") Long id) {
        var orderUpdated = orderService.update(order, id);
        return ResponseEntity.ok(orderUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
