package com.phoebus.service.ServiceImpl;

import com.phoebus.entites.Pedido;
import com.phoebus.exception.PedidoException;
import io.micronaut.core.annotation.NonNull;

import java.util.List;
import java.util.Optional;

public interface PedidoServiceImpl {

    List<Pedido> listAll();

    Pedido save(Pedido pedido);

    Optional<Pedido> findById(@NonNull Long id) throws PedidoException;

    void deletById (Long id) throws PedidoException;
}
