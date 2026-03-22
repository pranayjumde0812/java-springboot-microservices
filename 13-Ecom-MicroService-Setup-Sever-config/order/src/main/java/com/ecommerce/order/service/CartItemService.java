package com.ecommerce.order.service;

import com.ecommerce.order.dto.request.CartItemRequest;
import com.ecommerce.order.dto.response.CartItemResponse;
import com.ecommerce.order.model.CartItem;
import com.ecommerce.order.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public boolean addToCart(String userId, CartItemRequest cartItemRequest) {

        CartItem exitingcartItem = cartItemRepository.findByUserIdAndProductId(Long.valueOf(userId), cartItemRequest.getProductId());

        if (exitingcartItem != null) {
            exitingcartItem.setQuantity(exitingcartItem.getQuantity() + cartItemRequest.getQuantity());
            exitingcartItem.setPrice(BigDecimal.valueOf(100.00));
            cartItemRepository.save(exitingcartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUserId(Long.valueOf(userId));
            cartItem.setProductId(cartItemRequest.getProductId());
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(BigDecimal.valueOf(100.00));
            cartItemRepository.save(cartItem);
        }

        return true;
    }

    public boolean removeItemFromCart(String userId, Long productId) {

        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(Long.valueOf(userId), productId);
        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }

    public List<CartItemResponse> getAllCartItems(String userId) {

        return cartItemRepository.findByUserId(Long.valueOf(userId))
                .stream()
                .map(this::mapToUCartItemsResponse)
                .toList();
    }

    public List<CartItem> getCartItems(String userId) {

        return cartItemRepository.findByUserId(Long.valueOf(userId))
                .stream()
                .toList();

    }

    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(Long.valueOf(userId));
    }

    private CartItemResponse mapToUCartItemsResponse(CartItem cartItem) {
        CartItemResponse response = new CartItemResponse();

        response.setId(cartItem.getId().toString());
        response.setQuantity(cartItem.getQuantity());
        response.setPrice(cartItem.getPrice());
        response.setUserId(cartItem.getUserId().toString());
        response.setProductId(cartItem.getProductId().toString());

        return response;
    }

}
