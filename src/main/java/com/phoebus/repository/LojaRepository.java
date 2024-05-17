package com.phoebus.repository;

import com.phoebus.entites.Loja;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface LojaRepository extends CrudRepository<Loja,Long> {
}
