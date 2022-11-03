package com.seminario.store.services;

import com.seminario.store.models.ProductCategory;
import com.seminario.store.repositories.ProductCategoryRepository;
import commons.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory findById(Long id) {
        var optionalProductCategory = productCategoryRepository.findById(id);

        if (optionalProductCategory.isPresent()) {
            return optionalProductCategory.get();
        }

        throw new RecordNotFoundException();
    }

    @Override
    @Transactional
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    @Transactional
    public ProductCategory update(ProductCategory productCategory, Long id) {
        var optionalProductCategory = productCategoryRepository.findById(id);

        if (optionalProductCategory.isPresent()) {
            var productCategorySaved = optionalProductCategory.get();

            productCategorySaved.setCategory(productCategory.getCategory());
            productCategorySaved.setDescription(productCategory.getDescription());

            return productCategorySaved;
        } else {
            throw new RecordNotFoundException();
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        var optionalProductCategory = productCategoryRepository.findById(id);

        if (optionalProductCategory.isPresent()) {
            productCategoryRepository.delete(optionalProductCategory.get());
        } else {
            throw new RecordNotFoundException();
        }
    }
}
