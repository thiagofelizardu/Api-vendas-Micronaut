package com.phoebus.service.serviceImpl;

import com.phoebus.model.entites.Client;
import com.phoebus.model.entites.DTO.ClientDTO;
import com.phoebus.model.entites.DTO.AddressDTO;
import com.phoebus.model.entites.Address;
import com.phoebus.model.exception.ClientException;
import com.phoebus.repository.ClientRepository;
import com.phoebus.service.ClientService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {


    @Inject
    private final ClientRepository clienteRepository;

    public Page<ClientDTO> listAll(Pageable pageable) {
        Page<Client> clientesPage = clienteRepository.findAll(pageable);
        return clientesPage.map(ClientDTO::convertClientDTO);
    }

    public ClientDTO save(ClientDTO client) {
        Client cliente = new Client();
        cliente.setName(client.getName());
        cliente.setCpf(client.getCpf());
        cliente.setAge(client.getAge());
        Address address = setAddress(client.getAddress());
        cliente.setAddress(address);
        try {
            cliente = clienteRepository.save(cliente);
            return ClientDTO.convertClientDTO(cliente);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o cliente: " + e.getMessage());
        }
    }

    public ClientDTO findById(@NonNull Long id) throws ClientException {
        Client client = clienteRepository.findById(id)
                .orElseThrow(() -> new ClientException(id));
        return ClientDTO.convertClientDTO(client);
    }

    @Transactional
    public ClientDTO update(@NonNull Long id, ClientDTO client) throws ClientException {
        Client existingClient = clienteRepository.findById(id)
                .orElseThrow(() ->new ClientException(id));
        existingClient.setName(client.getName());
        existingClient.setCpf(client.getCpf());
        existingClient.setAge(client.getAge());
        Address address = setAddress(client.getAddress());
        existingClient.setAddress(address);
        try {
            Client updatedClient = clienteRepository.save(existingClient);
            return ClientDTO.convertClientDTO(updatedClient);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o cliente: " + e.getMessage());
        }
    }

    public void deleteById(Long id)throws ClientException {
        clienteRepository.findById(id)
                .orElseThrow(()-> new ClientException(id));
        clienteRepository.deleteById(id);
    }

    public Address setAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        return address;
    }

}
