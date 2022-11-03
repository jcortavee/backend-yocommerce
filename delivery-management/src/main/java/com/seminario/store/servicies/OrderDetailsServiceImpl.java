package com.seminario.store.servicies;

import com.seminario.store.models.OrderDetails;
import com.seminario.store.models.OrderStatus;
import com.seminario.store.repositories.OrderDetailsRepository;
import com.seminario.store.repositories.OrderStatusRepository;
import commons.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public OrderDetails findById(Long id) {
        Optional<OrderDetails> orderDetailsOptional = orderDetailsRepository.findById(id);

        if (orderDetailsOptional.isPresent()) {
            return orderDetailsOptional.get();
        }

        throw new RecordNotFoundException();
    }

    @Override
    @Transactional
    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    @Override
    @Transactional
    public OrderDetails update(OrderDetails orderDetails, Long id) {
        var orderDetailsStored = findById(id);

        orderDetailsStored.setPrice(orderDetails.getPrice());
        orderDetailsStored.setQuantity(orderDetails.getQuantity());
        orderDetailsStored.setComment(orderDetails.getComment());

        return orderDetailsStored;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var orderDetails = findById(id);
        orderDetailsRepository.delete(orderDetails);
    }
}
