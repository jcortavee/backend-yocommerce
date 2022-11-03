package com.seminario.store.resources;

import com.seminario.store.models.DeliveryPerson;
import com.seminario.store.models.Vehicle;
import com.seminario.store.services.DeliveryPersonService;
import com.seminario.store.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleResource {

    private final VehicleService vehicleService;

    public VehicleResource(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {
        List<Vehicle> vehicles = vehicleService.findAll();

        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable("id") Long id) {
        var vehicle = vehicleService.findById(id);

        return ResponseEntity.ok(vehicle);
    }

    @PostMapping
    public ResponseEntity<Vehicle> save(@RequestBody Vehicle vehicle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.save(vehicle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@RequestBody Vehicle vehicle, @PathVariable("id") Long id) {
        var vehicleUpdated = vehicleService.update(vehicle, id);

        return ResponseEntity.ok(vehicleUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        vehicleService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
