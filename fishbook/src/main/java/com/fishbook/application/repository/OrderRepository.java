package com.fishbook.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fishbook.application.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
