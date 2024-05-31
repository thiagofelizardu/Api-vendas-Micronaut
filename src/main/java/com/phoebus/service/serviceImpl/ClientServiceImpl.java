package com.phoebus.service.serviceImpl;

import com.phoebus.entites.Cliente;
import com.phoebus.entites.DTO.ClienteDTO;
import com.phoebus.exception.ClientException;
import com.phoebus.exception.ProdutoException;
import com.phoebus.repository.ClientRepository;
import com.phoebus.service.ClientService;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Inject
    private final ClientRepository clientRepository;

    public List<ClienteDTO> listAll() {
        List<Cliente> clientes = clientRepository.findAll();
        return clientes.stream()
                .map(ClienteDTO::convertClienteDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO save(ClienteDTO client) throws ClientException {
        Cliente cliente = new Cliente();
        cliente.setNome(client.getNome());
        cliente.setCpf(client.getCpf());
        cliente.setIdade(client.getIdade());
        cliente.setEndereco(client.getEndereco());
        try {
           cliente = clientRepository.save(cliente);
           return ClienteDTO.convertClienteDTO(cliente);
        }catch (Exception e){
            throw new ClientException("Erro ao salvar o cliente: " + e.getMessage());
        }
    }

    public ClienteDTO findById(@NonNull Long id) throws ClientException {
        Optional<Cliente> client = clientRepository.findById(id);
        if(client.isEmpty()){
            throw new ClientException("Cliente não encontrado com esse id: "+id);
        }
        return ClienteDTO.convertClienteDTO(client.get());
    }


    public ClienteDTO update(@NonNull Long id , ClienteDTO client) throws ClientException{
        Optional<Cliente> existingClient = clientRepository.findById(id);
        if (existingClient.isEmpty()){
            throw new ClientException("Cliente não encontrado com esse id: "+id);
        }
        Cliente updateClient = existingClient.get();
        updateClient.setNome(client.getNome());
        updateClient.setCpf(client.getCpf());
        updateClient.setIdade(client.getIdade());
        updateClient.setEndereco(client.getEndereco());
        try{
            updateClient = clientRepository.save(updateClient);
            return ClienteDTO.convertClienteDTO(updateClient);
        }catch (Exception e) {
            throw new ClientException("Erro ao atualizar o cliente: " + e.getMessage());
        }
    }

    public void deleteById(Long id)throws ClientException {
        Optional<Cliente> client = clientRepository.findById(id);
        if (client.isEmpty()){
            throw new ClientException("Cliente não encontrado com esse id: "+id);
        }
        clientRepository.deleteById(id);
    }

}
