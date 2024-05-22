package com.phoebus.service;

import com.phoebus.entites.Client;
import com.phoebus.exception.ClientException;
import io.micronaut.core.annotation.NonNull;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> listAll();

    Client save(Client client);

    Optional<Client> findById(@NonNull Long id) throws ClientException ;

    void deleteById(Long id) throws ClientException;

    Client update(@NonNull Long id ,Client client) throws ClientException;
}
