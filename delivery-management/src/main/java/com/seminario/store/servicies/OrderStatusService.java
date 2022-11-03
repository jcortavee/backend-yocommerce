package com.seminario.store.servicies;

import com.seminario.store.models.OrderStatus;

import java.util.List;

public interface OrderStatusService {
    List<OrderStatus> findAll();
    OrderStatus findById(Long id);
    OrderStatus save(OrderStatus orderStatus);
    OrderStatus update(OrderStatus orderStatus, Long id);
    void delete(Long id);
}
