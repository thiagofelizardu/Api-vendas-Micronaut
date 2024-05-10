package com.phoebus.service;

import com.phoebus.entites.Client;
import com.phoebus.exception.ClientException;
import com.phoebus.repository.ClientRepository;
import com.phoebus.service.ServiceImpl.ClientServiceImpl;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ClientService implements ClientServiceImpl {

    @Inject
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> listAll() {
        return clientRepository.findAll();
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> findById(@NonNull Long id) throws ClientException {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isEmpty()){
            throw new ClientException("Client Not Found");
        }
        return client;
    }

    public void deleteById(Long id)throws ClientException {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()){
            throw new ClientException("Client Not Found");
        }
        clientRepository.deleteById(id);
    }

}
