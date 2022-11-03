package com.seminario.store.services;

import com.seminario.store.models.Address;
import com.seminario.store.models.Role;

import java.util.List;

public interface AddressService {

    List<Address> findAll();
    Address findById(Long id);
    Address save(Address address);
    Address update(Address address, Long id);
    void delete(Long id);

}
