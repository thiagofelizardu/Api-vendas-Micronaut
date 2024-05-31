package com.phoebus.repository;

import com.phoebus.entites.Endereco;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

}
