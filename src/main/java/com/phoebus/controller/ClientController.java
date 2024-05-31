package com.phoebus.controller;

import com.phoebus.entites.Cliente;
import com.phoebus.entites.DTO.ClienteDTO;
import com.phoebus.exception.ClientException;
import com.phoebus.service.ClientService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Status;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;


import java.util.List;

@Controller("/client")
@ExecuteOn(TaskExecutors.IO)
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Get("/")
    @Status(HttpStatus.OK)
    public List<ClienteDTO> clientList()  throws ClientException{
        return clientService.listAll();
    }

    @Post
    @Status(HttpStatus.CREATED)
    public ClienteDTO clientSave(@Body ClienteDTO client) throws ClientException{
        try {
            return clientService.save(client);
        }catch (Exception e){
            throw  new ClientException("Erro em salvar cliente " + e.getMessage());
        }
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public ClienteDTO clientFindById(@PathVariable Long id) throws ClientException {
        return clientService.findById(id);
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    public ClienteDTO clientUpdate(@PathVariable Long id, @Body ClienteDTO clienteDTO) throws ClientException {
        return clientService.update(id, clienteDTO);
    }

    @Delete("delete/{id}")
    @Status(HttpStatus.ACCEPTED)
    public void ClientDeleteById(@PathVariable Long id) throws ClientException {
        clientService.deleteById(id);
    }
}
