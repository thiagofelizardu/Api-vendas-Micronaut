package com.phoebus.service;

import com.phoebus.entites.Cliente;
import com.phoebus.entites.DTO.ClienteDTO;
import com.phoebus.exception.ClientException;
import io.micronaut.core.annotation.NonNull;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<ClienteDTO> listAll() throws ClientException;

    ClienteDTO save(ClienteDTO client) throws ClientException;

    ClienteDTO findById(@NonNull Long id) throws ClientException ;

    void deleteById(Long id) throws ClientException;

    ClienteDTO update(@NonNull Long id , ClienteDTO client) throws ClientException;
}
