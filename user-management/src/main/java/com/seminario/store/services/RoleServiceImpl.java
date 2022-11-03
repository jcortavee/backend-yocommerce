package com.seminario.store.services;

import com.seminario.store.models.Role;
import com.seminario.store.models.request.RoleRequest;
import com.seminario.store.models.response.RoleResponse;
import com.seminario.store.repositories.RoleRepository;
import commons.exceptions.RecordNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        var role = roleRepository.findById(id);

        if (role.isPresent()) {
            return role.get();
        } else {
            throw new RecordNotFoundException("Role was not found");
        }
    }

    @Override
    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role update(Role role, Long id) {
        var roleOptional = roleRepository.findById(id);

        if (roleOptional.isPresent()) {
            var roleSaved = roleOptional.get();
            roleSaved.setName(role.getName());
            roleSaved.setDescription(role.getDescription());

            return roleSaved;
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var roleOptional = roleRepository.findById(id);

        if (roleOptional.isPresent()) {
            roleRepository.delete(roleOptional.get());
        } else {
            throw new RecordNotFoundException();
        }
    }
}
