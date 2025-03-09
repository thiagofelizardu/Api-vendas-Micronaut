package com.phoebus.service;

import com.phoebus.model.entites.DTO.ProductDTO;
import com.phoebus.model.exception.ProductException;
import io.micronaut.core.annotation.NonNull;

import java.util.List;

public interface ProdutoService {

    List<ProductDTO> listAll();

    ProductDTO saveProduto(ProductDTO productDTO) throws ProductException;

    ProductDTO findById(@NonNull Long id) throws ProductException;

    ProductDTO findByNome(@NonNull String nome) throws ProductException;

    void deleteById(@NonNull Long id) throws ProductException;

    ProductDTO updateProduto(@NonNull Long id, ProductDTO productDTO) throws ProductException;

}
