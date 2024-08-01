package com.phoebus.repository;

import com.phoebus.model.entites.OrderItem;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface ItemDoPedidoRepository extends JpaRepository<OrderItem,Long> {
}
