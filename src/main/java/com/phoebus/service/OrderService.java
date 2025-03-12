package com.phoebus.service;

import com.phoebus.model.entites.DTO.OrderDTO;

import com.phoebus.model.exception.ClientException;
import com.phoebus.model.exception.OrderException;
import com.phoebus.model.exception.ProductException;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

public interface OrderService {

    Page<OrderDTO> listAll(Pageable pageable);

    OrderDTO save(Long idCliente, OrderDTO orderDTO) throws ClientException, ProductException;

    OrderDTO findById(Long id) throws OrderException;

    void deleteById (Long id) throws OrderException;

    OrderDTO updatePedido(Long id, OrderDTO orderDTO) throws OrderException, ProductException, ClientException;
}
