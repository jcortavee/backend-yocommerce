package com.seminario.store.resources;

import com.seminario.store.models.ProductCategory;
import com.seminario.store.services.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-categories")
public class ProductCategoryResource {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryResource(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ProductCategory>> findAll() {
        var productCategories = productCategoryService.findAll();

        return ResponseEntity.ok(productCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> findById(@PathVariable("id") Long id) {
        var productCategory = productCategoryService.findById(id);

        return ResponseEntity.ok(productCategory);
    }

    @PostMapping
    public ResponseEntity<ProductCategory> save(@RequestBody ProductCategory productCategory) {
        var productCategorySaved = productCategoryService.save(productCategory);

        return ResponseEntity.status(HttpStatus.CREATED).body(productCategorySaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> update(@RequestBody ProductCategory productCategory,
                                                  @PathVariable("id") Long id) {
        var productCategoryUpdated = productCategoryService.update(productCategory, id);

        return ResponseEntity.ok(productCategoryUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productCategoryService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
