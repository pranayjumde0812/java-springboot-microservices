package com.ecommerce.order.repository;

import com.ecommerce.order.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserIdAndProductId(Long userId, Long productId);


    @Transactional
    void deleteByUserIdAndProductId(Long userId, Long productId);

    List<CartItem> findByUserId(Long userId);

    @Transactional
    void deleteByUserId(Long userId);
}
