package com.icebear2n2.orderService.domain.repository;

import com.icebear2n2.orderService.domain.entity.Cart;
import com.icebear2n2.orderService.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    boolean existsByCartId(Long cartId);
    Cart findByUser(User user);

    Page<Cart> findAllByUser(User user, Pageable pageable);
}
