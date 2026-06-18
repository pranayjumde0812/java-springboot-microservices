package com.ecommerce.order.repository;

import com.ecommerce.order.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserIdAndProductId(String userId, Long productId);


    @Transactional
    void deleteByUserIdAndProductId(String userId, Long productId);

    List<CartItem> findByUserId(String userId);

    @Transactional
    void deleteByUserId(String userId);
}
