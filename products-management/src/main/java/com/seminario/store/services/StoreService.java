package com.seminario.store.services;

import com.seminario.store.models.Store;
import com.seminario.store.models.StoreType;

import java.util.List;

public interface StoreService {

    List<Store> findAll();
    Store findById(Long id);
    Store save(Store store);
    Store update(Store store, Long id);
    void delete(Long id);

}
