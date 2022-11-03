package com.seminario.store.services;

import com.seminario.store.models.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> findAll();
    ProductCategory findById(Long id);
    ProductCategory save(ProductCategory productCategory);
    ProductCategory update(ProductCategory productCategory, Long id);
    void delete(Long id);
}
