package com.phoebus.service.serviceImpl;

import com.phoebus.entites.ItemPedido;
import com.phoebus.exception.ItemDoPedidoException;
import com.phoebus.repository.ItemDoPedidoRepository;
import com.phoebus.service.ItemDoPedidoService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ItemPedidoServiceImpl implements ItemDoPedidoService {

    @Inject
    private ItemDoPedidoRepository itemDoPedidoRepository;

    public List<ItemPedido> listAll() {
        return itemDoPedidoRepository.findAll();
    }


    public ItemPedido save(ItemPedido itemPedido) {
        return itemDoPedidoRepository.save(itemPedido);
    }


    public Optional<ItemPedido> findById(Long id) throws ItemDoPedidoException {
        return Optional.empty();
    }


    public void deletById(Long id) throws ItemDoPedidoException {

    }
}
