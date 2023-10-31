package com.icebear2n2.orderService.domain.repository;

import com.icebear2n2.orderService.domain.entity.cart.Cart;
import com.icebear2n2.orderService.domain.entity.cart.CartItem;
import com.icebear2n2.orderService.domain.entity.order.Order;
import com.icebear2n2.orderService.domain.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);


    Boolean existsByCartItemId(Long cartItemId);

    void deleteByCart(Cart cart);

    List<CartItem> findByOrder(Order order);

}
