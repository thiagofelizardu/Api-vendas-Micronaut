package com.phoebus.service.serviceImpl;

import com.phoebus.entites.Client;
import com.phoebus.exception.ClientException;
import com.phoebus.repository.ClientRepository;
import com.phoebus.service.ClientService;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Inject
    private final ClientRepository clientRepository;

    public List<Client> listAll() {
        return clientRepository.findAll();
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> findById(@NonNull Long id) throws ClientException {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isEmpty()){
            throw new ClientException("cliente não encontrado com esse id: "+id);
        }
        return client;
    }

    public Client update(@NonNull Long id ,Client client) throws ClientException{
        Optional<Client> existingClient = clientRepository.findById(id);
        if (existingClient.isEmpty()){
            throw new ClientException("cliente não encontrado com esse id: "+id);
        }
        Client updateClient = existingClient.get();
        updateClient.setName(client.getName());
        updateClient.setCpf(client.getCpf());
        updateClient.setAge(client.getAge());
        updateClient.setEndereco(client.getEndereco());
        return clientRepository.save(updateClient);
    }

    public void deleteById(Long id)throws ClientException {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()){
            throw new ClientException("cliente não encontrado com esse id: "+id);
        }
        clientRepository.deleteById(id);
    }

}
