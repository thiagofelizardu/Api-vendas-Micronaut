package com.phoebus.service;

import com.phoebus.entites.DTO.ItemDoPedidoDTO;
import com.phoebus.exception.ItemDoPedidoException;
import com.phoebus.exception.ProdutoException;


public interface ItemDoPedidoService {

    void deletById(Long id) throws ItemDoPedidoException;

    ItemDoPedidoDTO updateItemPedido(Long id, ItemDoPedidoDTO itemDoPedidoDTO) throws ItemDoPedidoException, ProdutoException;
}
