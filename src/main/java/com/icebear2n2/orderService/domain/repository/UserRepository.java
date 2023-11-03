package com.icebear2n2.orderService.domain.repository;

import com.icebear2n2.orderService.domain.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {


}