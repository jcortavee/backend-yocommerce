package com.seminario.store.resources;

import com.seminario.store.models.DeliveryPerson;
import com.seminario.store.models.User;
import com.seminario.store.services.DeliveryPersonService;
import com.seminario.store.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery-people")
public class DeliveryPersonResource {

    private final DeliveryPersonService deliveryPersonService;

    public DeliveryPersonResource(DeliveryPersonService deliveryPersonService) {
        this.deliveryPersonService = deliveryPersonService;
    }

    @GetMapping
    public ResponseEntity<List<DeliveryPerson>> findAll() {
        List<DeliveryPerson> deliveryPeople = deliveryPersonService.findAll();

        return ResponseEntity.ok(deliveryPeople);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryPerson> findById(@PathVariable("id") Long id) {
        var deliveryPerson = deliveryPersonService.findById(id);

        return ResponseEntity.ok(deliveryPerson);
    }

    @PostMapping
    public ResponseEntity<DeliveryPerson> save(@RequestBody DeliveryPerson deliveryPerson) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryPersonService.save(deliveryPerson));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryPerson> update(@RequestBody DeliveryPerson deliveryPerson, @PathVariable("id") Long id) {
        var deliveryPersonUpdated = deliveryPersonService.update(deliveryPerson, id);

        return ResponseEntity.ok(deliveryPersonUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        deliveryPersonService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
