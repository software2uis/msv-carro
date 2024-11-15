package com.example.demo.repository;

import com.example.demo.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RedisCartRepository implements CartRepository {

    private static final String CART_KEY_PREFIX = "cart:";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void addProduct(String userId, Product product) {
        try {
            String productJson = objectMapper.writeValueAsString(product);
            redisTemplate.opsForHash().put(CART_KEY_PREFIX + userId, product.getId(), productJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeProduct(String userId, String productId) {
        redisTemplate.opsForHash().delete(CART_KEY_PREFIX + userId, productId);
    }

    @Override
    public List<Product> getCartContents(String userId) {
        List<Product> products = new ArrayList<>();
        Map<Object, Object> cart = redisTemplate.opsForHash().entries(CART_KEY_PREFIX + userId);

        for (Object value : cart.values()) {
            try {
                Product product = objectMapper.readValue(value.toString(), Product.class);
                products.add(product);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    @Override
    public boolean cartExists(String userId) {
        return redisTemplate.hasKey(CART_KEY_PREFIX + userId);
    }

    @Override
    public void createCart(String userId) {
        if (!cartExists(userId)) {
            redisTemplate.opsForHash().put(CART_KEY_PREFIX + userId, "empty", "{}");  // Crear un carrito vacío
        }
    }
}
