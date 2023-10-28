package com.icebear2n2.purchaseService.domain.repository;

import com.icebear2n2.purchaseService.domain.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
