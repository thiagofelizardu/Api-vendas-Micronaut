package com.phoebus.repository;

import com.phoebus.entites.Cliente;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface ClientRepository extends JpaRepository<Cliente,Long> {
}
