package com.seminario.store.services;

import com.seminario.store.models.StoreType;
import com.seminario.store.repositories.StoreTypeRepository;
import commons.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreTypeServiceImpl implements StoreTypeService {

    private final StoreTypeRepository storeTypeRepository;

    public StoreTypeServiceImpl(StoreTypeRepository storeTypeRepository) {
        this.storeTypeRepository = storeTypeRepository;
    }

    @Override
    public List<StoreType> findAll() {
        return storeTypeRepository.findAll();
    }

    @Override
    public StoreType findById(Long id) {

        var storeType = storeTypeRepository.findById(id);

        if (storeType.isPresent()) {
            return storeType.get();
        }

        throw new RecordNotFoundException();
    }

    @Override
    @Transactional
    public StoreType save(StoreType storeType) {
        return storeTypeRepository.save(storeType);
    }

    @Override
    @Transactional
    public StoreType update(StoreType storeType, Long id) {
        var storeTypeOptional = storeTypeRepository.findById(id);

        if (storeTypeOptional.isPresent()) {
            var st = storeTypeOptional.get();

            st.setType(storeType.getType());
            st.setDescription(storeType.getDescription());

            return st;
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var optionalStoreType = storeTypeRepository.findById(id);

        if (optionalStoreType.isPresent()) {
            storeTypeRepository.delete(optionalStoreType.get());
        } else {
            throw new RecordNotFoundException();
        }
    }
}
