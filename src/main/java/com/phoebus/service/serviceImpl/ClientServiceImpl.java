package com.phoebus.service.serviceImpl;

import com.phoebus.model.entites.Client;
import com.phoebus.model.entites.DTO.ClientDTO;
import com.phoebus.model.entites.DTO.AddressDTO;
import com.phoebus.model.entites.Address;
import com.phoebus.model.exception.ClientException;
import com.phoebus.model.utils.EntityFinderUtils;
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
    private final ClientRepository clientRepository;

    public Page<ClientDTO> listAll(Pageable pageable) {
        Page<Client> clientesPage = clientRepository.findAll(pageable);
        return clientesPage.map(ClientDTO::convertClientDTO);
    }

    public ClientDTO save(ClientDTO client) {
        Client cliente = new Client();
        cliente.setName(client.getName());
        cliente.setCpf(client.getCpf());
        cliente.setAge(client.getAge());
        Address address = createAddress(client.getAddress());
        cliente.setAddress(address);
        try {
            cliente = clientRepository.save(cliente);
            return ClientDTO.convertClientDTO(cliente);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o cliente: " + e.getMessage());
        }
    }

    public ClientDTO findById(@NonNull Long id) throws ClientException {
        Client existingClient = EntityFinderUtils.findClientById(clientRepository, id);
        return ClientDTO.convertClientDTO(existingClient);
    }

    @Transactional
    public ClientDTO update(@NonNull Long id, ClientDTO client) throws ClientException {
        Client existingClient = EntityFinderUtils.findClientById(clientRepository, id);
        existingClient.setName(client.getName());
        existingClient.setCpf(client.getCpf());
        existingClient.setAge(client.getAge());
        Address address = createAddress(client.getAddress());
        existingClient.setAddress(address);
        try {
            Client updatedClient = clientRepository.save(existingClient);
            return ClientDTO.convertClientDTO(updatedClient);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o cliente: " + e.getMessage());
        }
    }

    public void deleteById(Long id)throws ClientException {
        EntityFinderUtils.findClientById(clientRepository, id);
        clientRepository.deleteById(id);
    }

    public Address createAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        return address;
    }

}
