package com.phoebus.service.ServiceImpl;


import com.phoebus.entites.Produto;
import com.phoebus.exception.ProdutoException;
import io.micronaut.core.annotation.NonNull;

import java.util.List;
import java.util.Optional;

public interface ProdutoServiceImpl {

    List<Produto> listAll();

    Produto save(Produto produto) throws ProdutoException;

    Optional<Produto> findById(@NonNull Long id) throws ProdutoException;

    void deletById (Long id) throws ProdutoException;

    Produto update (@NonNull Long id, Produto produto)throws ProdutoException;

}
