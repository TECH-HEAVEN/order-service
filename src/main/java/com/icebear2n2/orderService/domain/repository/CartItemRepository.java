package com.icebear2n2.orderService.domain.repository;

import com.icebear2n2.orderService.domain.entity.Cart;
import com.icebear2n2.orderService.domain.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);
    Boolean existsByCartItemId(Long cartItemId);
    void deleteByCart(Cart cart);


}
