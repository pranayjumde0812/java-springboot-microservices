package com.ecommerce.order.service;

import com.ecommerce.order.clients.ProductServiceClient;
import com.ecommerce.order.clients.UserServiceClient;
import com.ecommerce.order.dto.ProductResponse;
import com.ecommerce.order.dto.UserResponse;
import com.ecommerce.order.dto.request.CartItemRequest;
import com.ecommerce.order.dto.response.CartItemResponse;
import com.ecommerce.order.model.CartItem;
import com.ecommerce.order.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    private final ProductServiceClient productServiceClient;

    private final UserServiceClient userServiceClient;

    public boolean addToCart(String userId, CartItemRequest cartItemRequest) {
        // Look for Product
        ProductResponse productResponse = productServiceClient.getProductDetails(cartItemRequest.getProductId());

        if (productResponse == null || productResponse.getStockQuantity() < cartItemRequest.getQuantity())
            return false;

        UserResponse userResponse = userServiceClient.findUserDetails(userId);

        if (userResponse == null) return false;

        CartItem exitingcartItem = cartItemRepository
                .findByUserIdAndProductId(userId,
                        Long.valueOf(cartItemRequest.getProductId()));

        if (exitingcartItem != null) {
            exitingcartItem.setQuantity(exitingcartItem.getQuantity() + cartItemRequest.getQuantity());
            exitingcartItem.setPrice(BigDecimal.valueOf(100.00));
            cartItemRepository.save(exitingcartItem);
        } else {
        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setProductId(Long.valueOf(cartItemRequest.getProductId()));
        cartItem.setQuantity(cartItemRequest.getQuantity());
        cartItem.setPrice(BigDecimal.valueOf(100.00));
        cartItemRepository.save(cartItem);
        }

        return true;
    }

    public boolean removeItemFromCart(String userId, Long productId) {

        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }

    public List<CartItemResponse> getAllCartItems(String userId) {

        return cartItemRepository.findByUserId(userId)
                .stream()
                .map(this::mapToUCartItemsResponse)
                .toList();
    }

    public List<CartItem> getCartItems(String userId) {

        return cartItemRepository.findByUserId(userId)
                .stream()
                .toList();

    }

    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }

    private CartItemResponse mapToUCartItemsResponse(CartItem cartItem) {
        CartItemResponse response = new CartItemResponse();

        response.setId(cartItem.getId().toString());
        response.setQuantity(cartItem.getQuantity());
        response.setPrice(cartItem.getPrice());
        response.setUserId(cartItem.getUserId());
        response.setProductId(cartItem.getProductId().toString());

        return response;
    }

}
