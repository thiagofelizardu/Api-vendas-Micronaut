package com.phoebus.controller;

import com.phoebus.model.entites.DTO.OrderDTO;
import com.phoebus.model.exception.ClientException;
import com.phoebus.model.exception.PedidoException;
import com.phoebus.model.exception.ProdutoException;
import com.phoebus.service.PedidoService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@Controller("/pedido")
@ExecuteOn(TaskExecutors.IO)
@RequiredArgsConstructor
public class PedidoController {

    @Inject
    private final PedidoService pedidoService;

    @Get("/")
    @Status(HttpStatus.OK)
    public Page<OrderDTO> pedidoList(Pageable pageable){
        return pedidoService.listAll(pageable);
    }

    @Post("/Client/{idCliente}")
    @Status(HttpStatus.CREATED)
    public OrderDTO save(@PathVariable Long idCliente , @Body OrderDTO orderDTO) throws ClientException, ProdutoException {
        return pedidoService.save(idCliente, orderDTO);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public OrderDTO pedidoFindById(@PathVariable Long id)throws PedidoException {
        return pedidoService.findById(id);
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public void pedidoDeleteById(@PathVariable Long id)throws PedidoException {
        pedidoService.deleteById(id);
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    public OrderDTO pedidoUpdate(@PathVariable Long id, @Body OrderDTO pedido) throws PedidoException {
        return pedidoService.updatePedido(id,pedido);
    }

}
