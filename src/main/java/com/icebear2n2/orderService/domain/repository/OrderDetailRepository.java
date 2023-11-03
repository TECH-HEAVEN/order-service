package com.icebear2n2.orderService.domain.repository;

import com.icebear2n2.orderService.domain.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
