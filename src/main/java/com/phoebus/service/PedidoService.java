package com.phoebus.service;

import com.phoebus.entites.DTO.PedidoDTO;

import com.phoebus.exception.ClientException;
import com.phoebus.exception.PedidoException;
import com.phoebus.exception.ProdutoException;


import java.util.List;

public interface PedidoService {

    List<PedidoDTO> listAll();

    PedidoDTO save(Long idCliente, PedidoDTO pedidoDTO) throws ClientException, ProdutoException;

    PedidoDTO findById(Long id) throws PedidoException ;

    void deleteById (Long id) throws PedidoException;

    PedidoDTO updatePedido(Long id, PedidoDTO pedidoDTO) throws PedidoException;
}
