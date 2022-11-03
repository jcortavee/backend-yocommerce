package com.seminario.store.servicies;

import com.seminario.store.models.OrderDetails;
import com.seminario.store.models.OrderStatus;

import java.util.List;

public interface OrderDetailsService {
    List<OrderDetails> findAll();
    OrderDetails findById(Long id);
    OrderDetails save(OrderDetails orderDetails);
    OrderDetails update(OrderDetails orderDetails, Long id);
    void delete(Long id);
}
