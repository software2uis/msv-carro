package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class ProductRepository {

    @Autowired
    private RedisTemplate<String, Product> redisTemplate;

    private static final String KEY = "Product";

    public void save(Product product) {
        redisTemplate.opsForHash().put(KEY, product.getId(), product);
    }

    public Product findById(String id) {
        return (Product) redisTemplate.opsForHash().get(KEY, id);
    }

    public List<Product> findAll() {
        Set<Object> keys = redisTemplate.opsForHash().keys(KEY);
        List<Product> products = new ArrayList<>();
        for (Object key : keys) {
            products.add((Product) redisTemplate.opsForHash().get(KEY, key));
        }
        return products;
    }

    public void update(Product product) {
        save(product);  // En Redis, "save" también puede usarse para actualizar
    }

    public void delete(String id) {
        redisTemplate.opsForHash().delete(KEY, id);
    }
}
