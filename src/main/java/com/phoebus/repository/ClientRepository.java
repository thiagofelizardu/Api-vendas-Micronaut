package com.phoebus.repository;

import com.phoebus.entites.Client;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ClientRepository extends CrudRepository<Client,Long> {
}
