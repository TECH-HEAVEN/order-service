package com.icebear2n2.orderService.domain.repository;

import com.icebear2n2.orderService.domain.entity.cart.CartItem;
import com.icebear2n2.orderService.domain.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCartItems(List<CartItem> cartItems);
}
