package com.phoebus.service;

import com.phoebus.entites.Pedido;
import com.phoebus.exception.PedidoException;
import com.phoebus.repository.PedidoRepository;
import com.phoebus.service.ServiceImpl.PedidoServiceImpl;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class PedidoService implements PedidoServiceImpl {

    @Inject
    private PedidoRepository pedidoRepository;

    public List<Pedido> listAll() {
        return pedidoRepository.findAll();
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }


    public Optional<Pedido> findById(Long id) throws PedidoException {
        return Optional.empty();
    }


    public void deletById(Long id) throws PedidoException {

    }
}
