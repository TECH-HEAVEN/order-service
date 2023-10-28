package com.icebear2n2.purchaseService.domain.repository;

import com.icebear2n2.purchaseService.domain.entity.cart.Cart;
import com.icebear2n2.purchaseService.domain.entity.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);

    Boolean existsByCartItemId(Long cartItemId);

    void deleteByCart(Cart cart);

}
