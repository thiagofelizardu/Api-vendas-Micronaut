package com.phoebus.service;

import com.phoebus.model.entites.DTO.OrderDTO;

import com.phoebus.model.exception.ClientException;
import com.phoebus.model.exception.PedidoException;
import com.phoebus.model.exception.ProdutoException;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

public interface PedidoService {

    Page<OrderDTO> listAll(Pageable pageable);

    OrderDTO save(Long idCliente, OrderDTO orderDTO) throws ClientException, ProdutoException;

    OrderDTO findById(Long id) throws PedidoException ;

    void deleteById (Long id) throws PedidoException;

    OrderDTO updatePedido(Long id, OrderDTO orderDTO) throws PedidoException, ProdutoException;
}
