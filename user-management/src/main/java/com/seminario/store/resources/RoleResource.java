package com.seminario.store.resources;

import com.seminario.store.models.Role;
import com.seminario.store.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleResource {

    private final RoleService roleService;

    public RoleResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        List<Role> roles = roleService.findAll();

        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable("id") Long id) {
        var role = roleService.findById(id);

        return ResponseEntity.ok(role);
    }

    @PostMapping
    public ResponseEntity<Role> save(@RequestBody Role role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@RequestBody Role role, @PathVariable("id") Long id) {
        var updatedRole = roleService.update(role, id);

        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        roleService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
