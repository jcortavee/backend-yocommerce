package com.seminario.store.servicies;

import com.seminario.store.models.OrderStatus;
import com.seminario.store.repositories.OrderStatusRepository;
import commons.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public List<OrderStatus> findAll() {
        return orderStatusRepository.findAll();
    }

    @Override
    public OrderStatus findById(Long id) {
        Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(id);

        if (orderStatusOptional.isPresent()) {
            return orderStatusOptional.get();
        }

        throw new RecordNotFoundException();
    }

    @Override
    @Transactional
    public OrderStatus save(OrderStatus orderStatus) {
        return orderStatusRepository.save(orderStatus);
    }

    @Override
    @Transactional
    public OrderStatus update(OrderStatus orderStatus, Long id) {
        var orderStatusStored = findById(id);

        orderStatusStored.setDescription(orderStatus.getDescription());
        orderStatusStored.setStatus(orderStatus.getStatus());

        return orderStatusStored;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var orderStatus = findById(id);
        orderStatusRepository.delete(orderStatus);
    }
}
