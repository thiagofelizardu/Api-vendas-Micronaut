package com.phoebus.repository;

import com.phoebus.entites.ItemPedido;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface ItemDoPedidoRepository extends JpaRepository<ItemPedido,Long> {
}
