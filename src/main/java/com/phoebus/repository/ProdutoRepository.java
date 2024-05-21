package com.phoebus.repository;

import com.phoebus.entites.Produto;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto,Long> {

    Optional<Produto> findByNome(String nome);

}
