package com.phoebus.service;

import com.phoebus.model.entites.DTO.ProductDTO;
import com.phoebus.model.exception.ProdutoException;
import io.micronaut.core.annotation.NonNull;

import java.util.List;

public interface ProdutoService {

    List<ProductDTO> listAll();

    ProductDTO saveProduto(ProductDTO productDTO) throws ProdutoException;

    ProductDTO findById(@NonNull Long id) throws ProdutoException;

    ProductDTO findByNome(@NonNull String nome) throws ProdutoException;

    void deleteById(@NonNull Long id) throws ProdutoException;

    ProductDTO updateProduto(@NonNull Long id, ProductDTO productDTO) throws ProdutoException;

}
