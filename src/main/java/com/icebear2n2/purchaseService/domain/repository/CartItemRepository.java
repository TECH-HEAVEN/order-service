package com.icebear2n2.purchaseService.domain.repository;

import com.icebear2n2.purchaseService.domain.entity.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
