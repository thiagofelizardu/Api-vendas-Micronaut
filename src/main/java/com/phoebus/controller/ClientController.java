package com.phoebus.controller;

import com.phoebus.entites.Client;
import com.phoebus.exception.ClientException;
import com.phoebus.service.ClientService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;


import java.util.List;
import java.util.Optional;

@Controller("/client")
@ExecuteOn(TaskExecutors.IO)
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Get("/")
    @Status(HttpStatus.OK)
    public List<Client> ClientList() {
        return clientService.listAll();
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Client ClientSave(@Body Client client) {
        return clientService.save(client);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public Optional<Client> ClientFindById(@PathVariable Long id) throws ClientException {
        return clientService.findById(id);
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    public void ClientUpdate(@PathVariable Long id, @Body Client client) throws ClientException {
        clientService.update(id, client);
    }

    @Delete("delete/{id}")
    @Status(HttpStatus.ACCEPTED)
    public void ClientDeleteById(@PathVariable Long id) throws ClientException {
        clientService.deleteById(id);
    }
}
