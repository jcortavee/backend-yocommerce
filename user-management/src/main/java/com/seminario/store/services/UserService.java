package com.seminario.store.services;

import com.seminario.store.models.Role;
import com.seminario.store.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(Long id);
    User save(User user);
    User update(User user, Long id);
    void delete(Long id);

    User findByEmail(String email);
}
