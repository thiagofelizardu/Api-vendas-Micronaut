package com.phoebus.repository;

import com.phoebus.entites.Loja;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface LojaRepository extends JpaRepository<Loja,Long> {
}
