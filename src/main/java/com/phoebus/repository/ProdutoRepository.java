package com.phoebus.repository;

import com.phoebus.entites.Produto;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto,Long> {
}
