package com.app.ecom.service;

import com.app.ecom.dtos.request.CartItemRequest;
import com.app.ecom.dtos.response.CartItemResponse;
import com.app.ecom.model.CartItem;
import com.app.ecom.model.Product;
import com.app.ecom.model.User;
import com.app.ecom.repository.CartItemRepository;
import com.app.ecom.repository.ProductRepository;
import com.app.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public boolean addToCart(String userId, CartItemRequest cartItemRequest) {
        Optional<Product> productOpt = productRepository.findById(cartItemRequest.getProductId());

        if (productOpt.isEmpty()) return false;

        Product product = productOpt.get();

        if (product.getStockQuantity() < cartItemRequest.getQuantity()) return false;

        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));

        if (userOpt.isEmpty()) return false;

        User user = userOpt.get();

        CartItem exitingcartItem = cartItemRepository.findByUserAndProduct(user, product);

        if (exitingcartItem != null) {
            exitingcartItem.setQuantity(exitingcartItem.getQuantity() + cartItemRequest.getQuantity());
            exitingcartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(exitingcartItem.getQuantity())));
            cartItemRepository.save(exitingcartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
            cartItemRepository.save(cartItem);
        }

        return true;
    }

    public boolean removeItemFromCart(String userId, Long productId) {

        Optional<Product> productOpt = productRepository.findById(productId);
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));

        if (productOpt.isPresent() && userOpt.isPresent()) {
            cartItemRepository.deleteByUserAndProduct(userOpt.get(), productOpt.get());
            return true;
        }
        return false;
    }

    public List<CartItemResponse> getAllCartItems(String userId) {
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));

        if (userOpt.isPresent()) {
            return cartItemRepository.findByUser(userOpt.get())
                    .stream()
                    .map(this::mapToUCartItemsResponse)
                    .toList();
        }

        return new ArrayList<>();
    }

    public List<CartItem> getCartItems(String userId) {
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));

        if (userOpt.isPresent()) {
            return cartItemRepository.findByUser(userOpt.get())
                    .stream()
                    .toList();
        }

        return new ArrayList<>();
    }

    private CartItemResponse mapToUCartItemsResponse(CartItem cartItem) {
        CartItemResponse response = new CartItemResponse();

        response.setId(cartItem.getId().toString());
        response.setQuantity(cartItem.getQuantity());
        response.setPrice(cartItem.getPrice());
        response.setUserName(cartItem.getUser().getFirstName());
        response.setUserId(cartItem.getUser().getId().toString());
        response.setProductName(cartItem.getProduct().getName());
        response.setProductId(cartItem.getProduct().getId().toString());

        return response;
    }

    public void clearCart(String userId) {
        userRepository.findById(Long.valueOf(userId)).ifPresent(cartItemRepository::deleteByUser);
    }
}
