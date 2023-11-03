package com.icebear2n2.orderService.domain.repository;

import com.icebear2n2.orderService.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductId(Long productId);

}
