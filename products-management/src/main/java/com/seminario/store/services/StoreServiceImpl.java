package com.seminario.store.services;

import com.seminario.store.models.Store;
import com.seminario.store.repositories.StoreRepository;
import commons.exceptions.RecordNotFoundException;
import commons.rest.clients.UserClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserClient userClient;

    public StoreServiceImpl(StoreRepository storeRepository, UserClient userClient) {
        this.storeRepository = storeRepository;
        this.userClient = userClient;
    }

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store findById(Long id) {
        var optionalStore = storeRepository.findById(id);

        if (optionalStore.isPresent()) {
            return optionalStore.get();
        }

        throw new RecordNotFoundException();
    }

    @Override
    @Transactional
    public Store save(Store store) {
        var user = Optional.ofNullable(userClient.findById(store.getUserId()));

        if (user.isPresent()) {
            return storeRepository.save(store);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    @Transactional
    public Store update(Store store, Long id) {
        var optionalStore = storeRepository.findById(id);

        if (optionalStore.isPresent()) {
            var storeSaved = optionalStore.get();

            storeSaved.setStoreType(store.getStoreType());
            storeSaved.setName(store.getName());
            storeSaved.setDescription(store.getDescription());
            storeSaved.setPhone(store.getPhone());
            storeSaved.setEmail(store.getEmail());
            storeSaved.setAddress(store.getAddress());
            storeSaved.setStatus(store.getStatus());

            return storeSaved;
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var optionalStore = storeRepository.findById(id);

        if (optionalStore.isPresent()) {
            storeRepository.delete(optionalStore.get());
        } else {
            throw new RecordNotFoundException();
        }

    }
}
