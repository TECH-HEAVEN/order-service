package com.icebear2n2.orderService.domain.repository;

import com.icebear2n2.orderService.domain.entity.Order;
import com.icebear2n2.orderService.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByTrackingNumber(Long trackingNumber);

    Page<Order> findAllByUser(User user, Pageable pageable);
}
