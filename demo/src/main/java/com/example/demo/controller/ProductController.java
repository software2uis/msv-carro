package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody @Valid Product product) {
        productRepository.save(product);
    return ResponseEntity.ok("Producto creado satisfactoriamente");
    }


    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return productRepository.findById(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable String id, @RequestBody Product product) {
        product.setId(id);
        productRepository.update(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        productRepository.delete(id);
    return ResponseEntity.ok("Producto borrado satisfactoriamente");
}


    

    

}
