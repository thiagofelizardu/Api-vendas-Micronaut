package com.phoebus.controller;

import com.phoebus.entites.Pedido;
import com.phoebus.exception.PedidoException;
import com.phoebus.service.PedidoService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Controller("/pedido")
@ExecuteOn(TaskExecutors.IO)
@RequiredArgsConstructor
public class PedidoController {

    @Inject
    private final PedidoService pedidoService;

    @Get("/")
    @Status(HttpStatus.OK)
    public List<Pedido> pedidoList(){
        return pedidoService.listAll();
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Pedido pedidoSaved(@Body Pedido pedido)throws PedidoException{
        try {
            return pedidoService.save(pedido);
        }catch (Exception e){
            throw new PedidoException("Erro em salvar o Pedido " + e.getMessage());
        }
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public Optional<Pedido> pedidoFindById(@PathVariable Long id)throws PedidoException {
        return pedidoService.findById(id);
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public void pedidoDeleteById(@PathVariable Long id)throws PedidoException {
        pedidoService.deleteById(id);
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    public Pedido pedidoUpdate(@PathVariable Long id, @Body Pedido pedido) throws PedidoException {
        return pedidoService.updatePedido(id,pedido);
    }

}
