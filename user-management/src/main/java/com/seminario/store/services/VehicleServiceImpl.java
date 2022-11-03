package com.seminario.store.services;

import com.seminario.store.models.DeliveryPerson;
import com.seminario.store.models.Vehicle;
import com.seminario.store.repositories.DeliveryPersonRepository;
import com.seminario.store.repositories.VehicleRepository;
import commons.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle findById(Long id) {
        var vehicleOptional = vehicleRepository.findById(id);

        if (vehicleOptional.isPresent()) {
            return vehicleOptional.get();
        } else {
            throw new RecordNotFoundException("The vehicle is not found.");
        }
    }

    @Override
    @Transactional
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public Vehicle update(Vehicle vehicle, Long id) {
        var vehicleFetched = findById(id);

        vehicleFetched.setRegistrationIdentifier(vehicle.getRegistrationIdentifier());
        vehicleFetched.setColor(vehicle.getColor());
        vehicleFetched.setModel(vehicle.getModel());
        vehicleFetched.setBrand(vehicle.getBrand());

        return vehicleFetched;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var vehicleFetched = findById(id);
        vehicleRepository.delete(vehicleFetched);
    }
}
