package com.seminario.store.services;

import com.seminario.store.models.DeliveryPerson;
import com.seminario.store.models.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> findAll();
    Vehicle findById(Long id);
    Vehicle save(Vehicle vehicle);
    Vehicle update(Vehicle vehicle, Long id);
    void delete(Long id);

}
