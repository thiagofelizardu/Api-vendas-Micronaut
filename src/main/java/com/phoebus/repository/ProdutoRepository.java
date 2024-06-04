package com.phoebus.repository;

import com.phoebus.entites.Produto;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {


    Optional<Produto> findByNome(String nome);

}
