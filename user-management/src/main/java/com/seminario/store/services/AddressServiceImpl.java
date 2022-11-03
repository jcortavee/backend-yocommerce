package com.seminario.store.services;

import com.seminario.store.models.Address;
import com.seminario.store.models.Role;
import com.seminario.store.repositories.AddressRepository;
import com.seminario.store.repositories.RoleRepository;
import commons.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
        var address = addressRepository.findById(id);

        if (address.isPresent()) {
            return address.get();
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    @Transactional
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address update(Address address, Long id) {
        var addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()) {
            var addressSaved = addressOptional.get();
            addressSaved.setName(address.getName());
            addressSaved.setLatitude(address.getLatitude());
            addressSaved.setLongitude(address.getLongitude());
            addressSaved.setLine1(address.getLine1());
            addressSaved.setLine2(address.getLine2());
            addressSaved.setZipCode(address.getZipCode());

            return addressSaved;
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()) {
            addressRepository.delete(addressOptional.get());
        } else {
            throw new RecordNotFoundException();
        }
    }
}
