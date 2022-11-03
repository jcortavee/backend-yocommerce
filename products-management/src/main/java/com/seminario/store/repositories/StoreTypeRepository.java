package com.seminario.store.repositories;

import com.seminario.store.models.StoreType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StoreTypeRepository extends JpaRepository<StoreType, Long> {
}
