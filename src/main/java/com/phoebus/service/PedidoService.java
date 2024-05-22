package com.phoebus.service;

import com.phoebus.entites.Pedido;
import com.phoebus.exception.PedidoException;
import io.micronaut.core.annotation.NonNull;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    List<Pedido> listAll();

    Pedido save(Pedido pedido) throws PedidoException;

    Optional<Pedido> findById(@NonNull Long id) throws PedidoException;

    void deleteById (Long id) throws PedidoException;

    Pedido updatePedido(Long id , Pedido pedido)throws PedidoException;
}
