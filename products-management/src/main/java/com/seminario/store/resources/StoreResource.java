package com.seminario.store.resources;

import com.seminario.store.models.Store;
import com.seminario.store.models.StoreType;
import com.seminario.store.services.StoreService;
import com.seminario.store.services.StoreTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stores", produces = MediaType.APPLICATION_JSON_VALUE)
public class StoreResource {

    private final StoreService storeService;

    public StoreResource(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<Store>> findAll() {
        var stores = storeService.findAll();

        return ResponseEntity.ok(stores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> findById(@PathVariable("id") Long id) {
        var store = storeService.findById(id);

        return ResponseEntity.ok(store);
    }

    @PostMapping
    public ResponseEntity<Store> save(@RequestBody Store store) {
        var storeSaved = storeService.save(store);

        return ResponseEntity.status(HttpStatus.CREATED).body(storeSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> update(@RequestBody Store store, @PathVariable("id") Long id) {
        var updatedStore = storeService.update(store, id);

        return ResponseEntity.ok(updatedStore);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        storeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
