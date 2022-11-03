package com.seminario.store.controllers;

import com.seminario.store.models.OrderStatus;
import com.seminario.store.servicies.OrderStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-statuses")
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping
    public ResponseEntity<List<OrderStatus>> findAll() {
        List<OrderStatus> orderStatuses = orderStatusService.findAll();
        return ResponseEntity.ok(orderStatuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderStatus> findById(@PathVariable("id") Long id) {
        OrderStatus orderStatus = orderStatusService.findById(id);
        return ResponseEntity.ok(orderStatus);
    }

    @PostMapping
    public ResponseEntity<OrderStatus> save(@RequestBody OrderStatus orderStatus) {
        OrderStatus orderStatusSaved = orderStatusService.save(orderStatus);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderStatusSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderStatus> update(@RequestBody OrderStatus orderStatus,
                                              @PathVariable("id") Long id) {
        OrderStatus orderStatusUpdated = orderStatusService.update(orderStatus, id);
        return ResponseEntity.ok(orderStatusUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        orderStatusService.delete(id);

        return ResponseEntity.noContent().build();
    }


}
