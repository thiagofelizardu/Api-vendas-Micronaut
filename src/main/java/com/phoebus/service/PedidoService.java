package com.phoebus.service;

import com.phoebus.entites.DTO.PedidoDTO;

import com.phoebus.exception.ClientException;
import com.phoebus.exception.PedidoException;
import com.phoebus.exception.ProdutoException;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;


import java.util.List;

public interface PedidoService {

    Page<PedidoDTO> listAll(Pageable pageable);

    PedidoDTO save(Long idCliente, PedidoDTO pedidoDTO) throws ClientException, ProdutoException;

    PedidoDTO findById(Long id) throws PedidoException ;

    void deleteById (Long id) throws PedidoException;

    PedidoDTO updatePedido(Long id, PedidoDTO pedidoDTO) throws PedidoException;
}
