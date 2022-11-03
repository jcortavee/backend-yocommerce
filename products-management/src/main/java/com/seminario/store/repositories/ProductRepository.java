package com.seminario.store.repositories;

import com.seminario.store.models.Product;
import com.seminario.store.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
