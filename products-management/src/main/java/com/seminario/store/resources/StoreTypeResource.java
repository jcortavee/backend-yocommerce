package com.seminario.store.resources;

import com.seminario.store.models.StoreType;
import com.seminario.store.services.StoreTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store-types")
public class StoreTypeResource {

    private final StoreTypeService storeTypeService;

    public StoreTypeResource(StoreTypeService storeTypeService) {
        this.storeTypeService = storeTypeService;
    }

    @GetMapping
    public ResponseEntity<List<StoreType>> findAll() {
        var storeTypes = storeTypeService.findAll();

        return ResponseEntity.ok(storeTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreType> findById(@PathVariable("id") Long id) {
        var storeType = storeTypeService.findById(id);

        return ResponseEntity.ok(storeType);
    }

    @PostMapping
    public ResponseEntity<StoreType> save(@RequestBody StoreType storeType) {
        var storeTypeSaved = storeTypeService.save(storeType);

        return ResponseEntity.status(HttpStatus.CREATED).body(storeTypeSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreType> update(@RequestBody StoreType storeType, @PathVariable("id") Long id) {
        var updatedStoreType = storeTypeService.update(storeType, id);

        return ResponseEntity.ok(updatedStoreType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        storeTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
