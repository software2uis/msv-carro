package com.tuempresa.shoppingcart.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuempresa.shoppingcart.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RedisCartRepository implements CartRepository {

    private static final String CART_KEY = "cart";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void addProduct(Product product) {
        try {
            String productJson = objectMapper.writeValueAsString(product);
            redisTemplate.opsForHash().put(CART_KEY, product.getId(), productJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeProduct(String id) {
        redisTemplate.opsForHash().delete(CART_KEY, id);
    }

    @Override
    public List<Product> getCartContents() {
        List<Product> products = new ArrayList<>();
        Map<Object, Object> cart = redisTemplate.opsForHash().entries(CART_KEY);

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
    public double calculateTotal() {
        double total = 0.0;
        Map<Object, Object> cart = redisTemplate.opsForHash().entries(CART_KEY);

        for (Object value : cart.values()) {
            try {
                Product product = objectMapper.readValue(value.toString(), Product.class);
                total += product.getPrice(); // Suma el precio de cada producto
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return total;
    }


}
