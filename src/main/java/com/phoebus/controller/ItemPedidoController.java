package com.phoebus.controller;

import com.phoebus.model.entites.DTO.OrderItemDTO;
import com.phoebus.model.exception.ItemDoPedidoException;
import com.phoebus.model.exception.ProdutoException;
import com.phoebus.service.ItemDoPedidoService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@Controller("/itempedido")
@ExecuteOn(TaskExecutors.IO)
@RequiredArgsConstructor
public class ItemPedidoController {

    @Inject
    private ItemDoPedidoService itemDoPedidoService;

    @Delete("delete/{id}")
    @Status(HttpStatus.OK)
    public void itemDoPedidoDeleteById(@PathVariable Long id) throws ItemDoPedidoException {
        itemDoPedidoService.deletById(id);
    }

    @Put("/{id}")
    public OrderItemDTO updateItemDoPedido(Long id, OrderItemDTO orderItemDTO) throws ItemDoPedidoException, ProdutoException {
        return itemDoPedidoService.updateItemPedido(id, orderItemDTO);
    }
}
