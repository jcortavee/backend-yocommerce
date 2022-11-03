package com.seminario.store.resources;

import com.seminario.store.models.Product;
import com.seminario.store.models.ProductCategory;
import com.seminario.store.services.ProductCategoryService;
import com.seminario.store.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        var products = productService.findAll();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
        var product = productService.findById(id);

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        var productSaved = productService.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product,
                                                  @PathVariable("id") Long id) {
        var productUpdated = productService.update(product, id);

        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
