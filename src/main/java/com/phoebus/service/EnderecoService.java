package com.phoebus.service;

import com.phoebus.entites.Endereco;
import com.phoebus.exception.EnderecoException;
import io.micronaut.core.annotation.NonNull;

import java.util.List;
import java.util.Optional;

public interface EnderecoService {

    List<Endereco> listAll();

    Endereco save(Endereco endereco);

    Optional<Endereco> findById(@NonNull Long id) throws EnderecoException;

    void deletById (Long id) throws EnderecoException;


}
