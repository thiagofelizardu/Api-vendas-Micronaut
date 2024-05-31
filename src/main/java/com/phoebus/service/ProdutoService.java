package com.phoebus.service;

import com.phoebus.entites.DTO.ProdutoDTO;
import com.phoebus.exception.ProdutoException;
import io.micronaut.core.annotation.NonNull;

import java.util.List;

public interface ProdutoService {

    List<ProdutoDTO> listAll();

    ProdutoDTO saveProduto(Long lojaId, ProdutoDTO produtoDTO) throws ProdutoException;

    ProdutoDTO findById(@NonNull Long id) throws ProdutoException;

    ProdutoDTO findByNome(@NonNull String nome) throws ProdutoException;

    void deleteById(@NonNull Long id) throws ProdutoException;

    ProdutoDTO updateProduto(@NonNull Long id, ProdutoDTO produtoDTO) throws ProdutoException;
}
