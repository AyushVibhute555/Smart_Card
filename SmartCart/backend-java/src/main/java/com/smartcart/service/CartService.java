package com.smartcart.service;

import com.smartcart.model.CartItem;
import com.smartcart.model.Product;
import com.smartcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    private final ProductRepository repo;

    private final List<CartItem> cart = new ArrayList<>();

    @Autowired
    public CartService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void addItem(String name, int qty) {
        Product product = repo.findByNameIgnoreCase(name).orElseThrow(() ->
                new RuntimeException("Product not found: " + name)
        );
        cart.add(new CartItem(product, qty));
    }

    public void clearCart() {
        cart.clear();
    }

    public double getTotal() {
        return cart.stream().mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity()).sum();
    }
}
