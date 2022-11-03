package com.seminario.store.services;

import com.seminario.store.models.DeliveryPerson;
import com.seminario.store.models.User;

import java.util.List;

public interface DeliveryPersonService {

    List<DeliveryPerson> findAll();
    DeliveryPerson findById(Long id);
    DeliveryPerson save(DeliveryPerson deliveryPerson);
    DeliveryPerson update(DeliveryPerson deliveryPerson, Long id);
    void delete(Long id);

}
