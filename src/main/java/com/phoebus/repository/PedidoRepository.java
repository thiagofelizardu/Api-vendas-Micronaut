package com.phoebus.repository;

import com.phoebus.entites.Pedido;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido,Long> {
}
