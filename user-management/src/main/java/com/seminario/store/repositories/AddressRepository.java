package com.seminario.store.repositories;

import com.seminario.store.models.Address;
import com.seminario.store.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
