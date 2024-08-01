package com.phoebus.repository;

import com.phoebus.model.entites.Order;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface PedidoRepository extends JpaRepository<Order,Long> {
}
