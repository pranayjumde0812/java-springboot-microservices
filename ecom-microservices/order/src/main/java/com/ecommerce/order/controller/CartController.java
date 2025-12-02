package com.ecommerce.order.controller;


import com.ecommerce.order.dto.request.CartItemRequest;
import com.ecommerce.order.dto.response.CartItemResponse;
import com.ecommerce.order.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartItemService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId,
                                            @RequestBody CartItemRequest cartItemRequest) {

        if (!cartService.addToCart(userId, cartItemRequest))
            return ResponseEntity.badRequest().body("Product out of stock or User not found or product not found");

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeItemFromCart(@RequestHeader("X-User-ID") String userId,
                                                   @PathVariable Long productId) {
        boolean deleted = cartService.removeItemFromCart(userId, productId);

        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItemResponse>> getAllItemFromCart(@RequestHeader("X-User-ID") String userId) {
        List<CartItemResponse> cartItemList = cartService.getAllCartItems(userId);

        return new ResponseEntity<>(cartItemList, HttpStatus.OK);
    }

}
