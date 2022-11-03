package com.seminario.store.controllers;

import com.seminario.store.models.Order;
import com.seminario.store.models.OrderDetails;
import com.seminario.store.servicies.OrderDetailsService;
import com.seminario.store.servicies.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetails>> findAll() {
        var orderDetails = orderDetailsService.findAll();
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetails> findById(@PathVariable("id") Long id) {
        var orderDetails = orderDetailsService.findById(id);
        return ResponseEntity.ok(orderDetails);
    }

    @PostMapping
    public ResponseEntity<OrderDetails> save(@RequestBody OrderDetails orderDetails) {
        var orderDetailsSaved = orderDetailsService.save(orderDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailsSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetails> update(@RequestBody OrderDetails orderDetails, @PathVariable("id") Long id) {
        var orderUpdated = orderDetailsService.update(orderDetails, id);
        return ResponseEntity.ok(orderUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        orderDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
