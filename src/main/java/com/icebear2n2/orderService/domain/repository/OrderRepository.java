package com.icebear2n2.orderService.domain.repository;

import com.icebear2n2.orderService.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByTrackingNumber(Long trackingNumber);

}
