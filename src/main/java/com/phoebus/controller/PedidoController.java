package com.phoebus.controller;

import com.phoebus.entites.Pedido;
import com.phoebus.exception.PedidoException;
import com.phoebus.service.PedidoService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Controller("/pedido")
@ExecuteOn(TaskExecutors.IO)
public class PedidoController {

    @Inject
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Get("/")
    @Status(HttpStatus.OK)
    public List<Pedido> pedidoList(){
        return pedidoService.listAll();
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Pedido pedidoSaved(@Body Pedido pedido)throws PedidoException{
        return pedidoService.save(pedido);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public Optional<Pedido> produtoFindById(@PathVariable Long id)throws PedidoException {
        return pedidoService.findById(id);
    }


    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public void produtoDeleteById(@PathVariable Long id)throws PedidoException {
        pedidoService.deleteById(id);
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    public Pedido produtoUpdate(@PathVariable Long id, @Body Pedido pedido) throws PedidoException {
        return pedidoService.updatePedido(id,pedido);
    }


}
