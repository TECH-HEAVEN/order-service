package com.icebear2n2.purchaseService.domain.repository;

import com.icebear2n2.purchaseService.domain.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
