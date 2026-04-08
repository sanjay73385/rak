package com.pinkaura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pinkaura.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}