package com.seminario.store.repositories;

import com.seminario.store.models.DeliveryPerson;
import com.seminario.store.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
