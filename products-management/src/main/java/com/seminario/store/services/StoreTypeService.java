package com.seminario.store.services;

import com.seminario.store.models.StoreType;

import java.util.List;

public interface StoreTypeService {

    List<StoreType> findAll();
    StoreType findById(Long id);
    StoreType save(StoreType storeType);
    StoreType update(StoreType storeType, Long id);
    void delete(Long id);

}
