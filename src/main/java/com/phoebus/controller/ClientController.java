package com.phoebus.controller;

import com.phoebus.model.entites.DTO.ClientDTO;
import com.phoebus.model.exception.ClientException;
import com.phoebus.service.ClientService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
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

@Controller("/client")
@ExecuteOn(TaskExecutors.IO)
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Get("/")
    @Status(HttpStatus.OK)
    public Page<ClientDTO> listAll(Pageable pageable) throws ClientException {
        return clientService.listAll(pageable);
    }

    @Post
    @Status(HttpStatus.CREATED)
    public ClientDTO clientSaved(@Body ClientDTO client){
        return clientService.save(client);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public ClientDTO clientFindById(@PathVariable Long id) throws ClientException {
        return clientService.findById(id);
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    public ClientDTO clientUpdate(@PathVariable Long id, @Body ClientDTO clientDTO) throws ClientException {
        return clientService.update(id, clientDTO);
    }

    @Delete("delete/{id}")
    @Status(HttpStatus.OK)
    public void ClientDeleteById(@PathVariable Long id) throws ClientException {
        clientService.deleteById(id);
    }
}
