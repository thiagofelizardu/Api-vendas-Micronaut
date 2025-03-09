package com.phoebus.repository;

import com.phoebus.model.entites.OrderItem;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
