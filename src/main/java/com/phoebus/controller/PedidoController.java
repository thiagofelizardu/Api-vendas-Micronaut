package com.phoebus.controller;

import com.phoebus.entites.DTO.PedidoDTO;
import com.phoebus.exception.ClientException;
import com.phoebus.exception.PedidoException;
import com.phoebus.exception.ProdutoException;
import com.phoebus.service.PedidoService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller("/pedido")
@ExecuteOn(TaskExecutors.IO)
@RequiredArgsConstructor
public class PedidoController {

    @Inject
    private final PedidoService pedidoService;

    @Get("/")
    @Status(HttpStatus.OK)
    public List<PedidoDTO> pedidoList(){
        return pedidoService.listAll();
    }

    @Post("/Client/{idCliente}")
    @Status(HttpStatus.CREATED)
    public PedidoDTO save(@PathVariable Long idCliente ,@Body PedidoDTO pedidoDTO) throws ClientException, ProdutoException {
        return pedidoService.save(idCliente,pedidoDTO);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public PedidoDTO pedidoFindById(@PathVariable Long id)throws PedidoException {
        return pedidoService.findById(id);
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public void pedidoDeleteById(@PathVariable Long id)throws PedidoException {
        pedidoService.deleteById(id);
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    public PedidoDTO pedidoUpdate(@PathVariable Long id, @Body PedidoDTO pedido) throws PedidoException {
        return pedidoService.updatePedido(id,pedido);
    }

}
