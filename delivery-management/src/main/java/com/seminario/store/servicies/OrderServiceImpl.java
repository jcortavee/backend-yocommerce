package com.seminario.store.servicies;

import com.seminario.store.models.Order;
import com.seminario.store.repositories.OrderRepository;
import commons.exceptions.RecordNotFoundException;
import commons.rest.clients.StoreClient;
import commons.rest.clients.UserClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final StoreClient storeClient;

    public OrderServiceImpl(OrderRepository orderRepository, UserClient userClient, StoreClient storeClient) {
        this.orderRepository = orderRepository;
        this.userClient = userClient;
        this.storeClient = storeClient;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            return orderOptional.get();
        }

        throw new RecordNotFoundException("Order was not found");
    }

    @Override
    @Transactional
    public Order save(Order order) {
        var user = Optional.ofNullable(userClient.findById(order.getUserId()));
        var store = Optional.ofNullable(storeClient.findStoreById(order.getStoreId()));

        if (user.isPresent() && store.isPresent()) {
            order.setCreatedAt(LocalDateTime.now());
            return orderRepository.save(order);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    @Transactional
    public Order update(Order order, Long id) {
        var orderStored = findById(id);

        orderStored.setAddressId(orderStored.getAddressId());
        orderStored.setOrderStatus(order.getOrderStatus());
        orderStored.setOrderNumber(order.getOrderNumber());
        orderStored.setComment(order.getComment());

        return orderStored;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var order = findById(id);
        orderRepository.delete(order);
    }
}
