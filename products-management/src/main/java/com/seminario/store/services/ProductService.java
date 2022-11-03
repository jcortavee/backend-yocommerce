package com.seminario.store.services;

import com.seminario.store.models.Product;
import com.seminario.store.models.ProductCategory;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    Product update(Product product, Long id);
    void delete(Long id);
}
