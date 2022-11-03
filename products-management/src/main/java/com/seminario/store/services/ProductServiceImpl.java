package com.seminario.store.services;

import com.seminario.store.models.Product;
import com.seminario.store.models.ProductCategory;
import com.seminario.store.repositories.ProductCategoryRepository;
import com.seminario.store.repositories.ProductRepository;
import commons.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        var optionalCategory = productRepository.findById(id);

        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }

        throw new RecordNotFoundException();
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(Product product, Long id) {
        var optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            var productSaved = optionalProduct.get();

            productSaved.setName(product.getName());
            productSaved.setDescription(product.getDescription());
            productSaved.setPrice(product.getPrice());
            productSaved.setStock(product.getStock());
            productSaved.setStatus(product.getStatus());
            productSaved.setStore(product.getStore());
            productSaved.setProductCategory(product.getProductCategory());
        }

        throw new RecordNotFoundException();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
        } else {
            throw new RecordNotFoundException();
        }
    }
}
