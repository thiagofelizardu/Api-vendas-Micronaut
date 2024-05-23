package com.phoebus.service;


import com.phoebus.entites.DTO.ProdutoDTO;
import com.phoebus.entites.Produto;
import com.phoebus.exception.ProdutoException;
import io.micronaut.core.annotation.NonNull;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    List<Produto> listAll();

    Produto savedProduto(Long lojaId, ProdutoDTO produtoDTO ) throws ProdutoException;

    Optional<Produto> findById(@NonNull Long id) throws ProdutoException;

    void deletById (Long id) throws ProdutoException;

    Produto updateProduto (@NonNull Long id, Produto produto) throws ProdutoException;

}
