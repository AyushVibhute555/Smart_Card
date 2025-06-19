package com.smartcart.controller;

import com.smartcart.model.CartItem;
import com.smartcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;


public class CartController {


    private CartService service;


    public String addToCart(Map<String, Object> payload) {
        String name = (String) payload.get("name");
        int qty = (int) payload.get("quantity");
        service.addItem(name, qty);
        return "Added";
    }


    public List<CartItem> getCart() {
        return service.getCart();
    }


    public void clearCart() {
        service.clearCart();
    }


    public double getTotal() {
        return service.getTotal();
    }
}