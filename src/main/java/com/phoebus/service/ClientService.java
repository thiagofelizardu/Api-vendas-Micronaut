package com.phoebus.service;


import com.phoebus.entites.DTO.ClienteDTO;
import com.phoebus.exception.ClientException;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;


public interface ClientService {

    Page<ClienteDTO> listAll(Pageable pageable) throws ClientException;

    ClienteDTO save(ClienteDTO client);

    ClienteDTO findById(@NonNull Long id) throws ClientException ;

    void deleteById(Long id) throws ClientException;

    ClienteDTO update(@NonNull Long id , ClienteDTO client) throws ClientException;
}
