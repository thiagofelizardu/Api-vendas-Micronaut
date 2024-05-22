package com.phoebus.service.serviceImpl;

import com.phoebus.entites.Client;
import com.phoebus.exception.ClientException;
import com.phoebus.repository.ClientRepository;
import com.phoebus.service.ClientService;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ClientServiceImpl implements ClientService {

    @Inject
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
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

    public Client update(@NonNull Long id ,Client client) throws ClientException{
        Optional<Client> existingClient = clientRepository.findById(id);
        if (existingClient.isEmpty()){
            throw new ClientException("Client Not Found");
        }
        Client updateClient = existingClient.get();
        updateClient.setName(client.getName());
        updateClient.setCpf(client.getCpf());
        updateClient.setAge(client.getAge());
        updateClient.setCity(client.getCity());
        return clientRepository.save(updateClient);
    }

    public void deleteById(Long id)throws ClientException {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()){
            throw new ClientException("Client Not Found");
        }
        clientRepository.deleteById(id);
    }

}
