package com.phoebus.service;

import com.phoebus.model.entites.DTO.OrderItemDTO;
import com.phoebus.model.exception.OrderItemException;
import com.phoebus.model.exception.ProductException;


public interface OrderItemService {

    void deletById(Long id) throws OrderItemException;

    OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO) throws OrderItemException, ProductException;
}
