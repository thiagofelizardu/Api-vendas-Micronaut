package com.phoebus.repository;

import com.phoebus.model.entites.Client;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
