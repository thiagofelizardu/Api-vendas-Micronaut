package com.phoebus.repository;

import com.phoebus.entites.ItemPedido;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ItemDoPedidoRepository extends CrudRepository<ItemPedido,Long> {
}
