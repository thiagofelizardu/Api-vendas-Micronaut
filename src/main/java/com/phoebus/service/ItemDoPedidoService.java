package com.phoebus.service;

import com.phoebus.model.entites.DTO.OrderItemDTO;
import com.phoebus.model.exception.ItemDoPedidoException;
import com.phoebus.model.exception.ProdutoException;


public interface ItemDoPedidoService {

    void deletById(Long id) throws ItemDoPedidoException;

    OrderItemDTO updateItemPedido(Long id, OrderItemDTO orderItemDTO) throws ItemDoPedidoException, ProdutoException;
}
