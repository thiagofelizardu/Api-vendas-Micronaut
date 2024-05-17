package com.phoebus.service;

import com.phoebus.entites.Produto;
import com.phoebus.exception.ProdutoException;
import com.phoebus.repository.ProdutoRepository;
import com.phoebus.service.ServiceImpl.ProdutoServiceImpl;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ProdutoService implements ProdutoServiceImpl {


    @Inject
    private ProdutoRepository produtoRepository;

    public List<Produto> listAll() {
        return produtoRepository.findAll();
    }


    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }


    public Optional<Produto> findById(Long id) throws ProdutoException {
        return Optional.empty();
    }


    public void deletById(Long id) throws ProdutoException {

    }
}
