package com.phoebus.service.serviceImpl;

import com.phoebus.entites.Cliente;
import com.phoebus.entites.DTO.ClienteDTO;
import com.phoebus.entites.DTO.EnderecoDTO;
import com.phoebus.entites.Endereco;
import com.phoebus.exception.ClientException;
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

    public Page<ClienteDTO> listAll(Pageable pageable) {
        Page<Cliente> clientesPage = clienteRepository.findAll(pageable);
        return clientesPage.map(ClienteDTO::convertClienteDTO);
    }

    public ClienteDTO save(ClienteDTO client) {
        Cliente cliente = new Cliente();
        cliente.setNome(client.getNome());
        cliente.setCpf(client.getCpf());
        cliente.setIdade(client.getIdade());
        Endereco endereco = EnderecoDTO.convertToEndereco(client.getEndereco());
        cliente.setEndereco(endereco);
        try {
            cliente = clienteRepository.save(cliente);
            return ClienteDTO.convertClienteDTO(cliente);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o cliente: " + e.getMessage());
        }
    }

    public ClienteDTO findById(@NonNull Long id) throws ClientException {
        Cliente client = clienteRepository.findById(id)
                .orElseThrow(() -> new ClientException(id));
        return ClienteDTO.convertClienteDTO(client);
    }

    @Transactional
    public ClienteDTO update(@NonNull Long id, ClienteDTO client) throws ClientException {
        Cliente existingClient = clienteRepository.findById(id)
                .orElseThrow(() ->new ClientException(id));
        existingClient.setNome(client.getNome());
        existingClient.setCpf(client.getCpf());
        existingClient.setIdade(client.getIdade());
        Endereco endereco = EnderecoDTO.convertToEndereco(client.getEndereco());
        existingClient.setEndereco(endereco);
        try {
            Cliente updatedClient = clienteRepository.save(existingClient);
            return ClienteDTO.convertClienteDTO(updatedClient);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o cliente: " + e.getMessage());
        }
    }


    public void deleteById(Long id)throws ClientException {
        clienteRepository.findById(id)
                .orElseThrow(()-> new ClientException(id));
        clienteRepository.deleteById(id);
    }

}
