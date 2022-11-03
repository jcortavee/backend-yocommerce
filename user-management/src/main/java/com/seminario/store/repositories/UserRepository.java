package com.seminario.store.repositories;

import com.seminario.store.models.Role;
import com.seminario.store.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User u where u.email = ?1")
    Optional<User> findUserByEmail(String email);
}
