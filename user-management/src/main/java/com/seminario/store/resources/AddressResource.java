package com.seminario.store.resources;

import com.seminario.store.models.Address;
import com.seminario.store.models.User;
import com.seminario.store.services.AddressService;
import com.seminario.store.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressResource {

    private final AddressService addressService;

    public AddressResource(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>> findAll() {
        List<Address> addresses = addressService.findAll();

        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findById(@PathVariable("id") Long id) {
        var address = addressService.findById(id);

        return ResponseEntity.ok(address);
    }

    @PostMapping
    public ResponseEntity<Address> save(@RequestBody Address address) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.save(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@RequestBody Address address, @PathVariable("id") Long id) {
        var updatedAddress = addressService.update(address, id);

        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        addressService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
