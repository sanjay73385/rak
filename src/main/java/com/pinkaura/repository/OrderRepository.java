package com.pinkaura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pinkaura.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
}