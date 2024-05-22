package com.phoebus.repository;

import com.phoebus.entites.Endereco;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco,Long> {

}
