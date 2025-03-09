package com.phoebus.controller;

import com.phoebus.model.entites.DTO.OrderItemDTO;
import com.phoebus.model.exception.OrderItemException;
import com.phoebus.model.exception.ProductException;
import com.phoebus.service.OrderItemService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@Controller("/itempedido")
@ExecuteOn(TaskExecutors.IO)
@RequiredArgsConstructor
public class OrderItemController {

    @Inject
    private OrderItemService orderItemService;

    @Delete("delete/{id}")
    @Status(HttpStatus.OK)
    public void itemDoPedidoDeleteById(@PathVariable Long id) throws OrderItemException {
        orderItemService.deletById(id);
    }

    @Put("/{id}")
    public OrderItemDTO updateItemDoPedido(Long id, OrderItemDTO orderItemDTO) throws OrderItemException, ProductException {
        return orderItemService.updateOrderItem(id, orderItemDTO);
    }
}
