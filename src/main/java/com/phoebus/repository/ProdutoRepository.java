package com.phoebus.repository;

import com.phoebus.model.entites.Product;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Product,Long> {


    Optional<Product> findByName(String name);

}
