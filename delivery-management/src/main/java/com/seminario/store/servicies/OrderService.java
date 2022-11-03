package com.seminario.store.servicies;

import com.seminario.store.models.Order;
import com.seminario.store.models.OrderStatus;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(Long id);
    Order save(Order order);
    Order update(Order order, Long id);
    void delete(Long id);
}
