package com.phoebus.service;


import com.phoebus.model.entites.DTO.ClientDTO;
import com.phoebus.model.exception.ClientException;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;


public interface ClientService {

    Page<ClientDTO> listAll(Pageable pageable) throws ClientException;

    ClientDTO save(ClientDTO client);

    ClientDTO findById(@NonNull Long id) throws ClientException ;

    void deleteById(Long id) throws ClientException;

    ClientDTO update(@NonNull Long id , ClientDTO client) throws ClientException;
}
