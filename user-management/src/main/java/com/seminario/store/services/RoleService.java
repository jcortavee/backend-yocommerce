package com.seminario.store.services;

import com.seminario.store.models.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();
    Role findById(Long id);
    Role save(Role role);
    Role update(Role role, Long id);
    void delete(Long id);

}
