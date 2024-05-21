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
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listAll() {
        return produtoRepository.findAll();
    }

    public Produto save(Produto produto) throws ProdutoException {
        Optional<Produto> existingProduto = produtoRepository.findByNome(produto.getNome());
        if (existingProduto.isPresent()) {
            throw new ProdutoException("Já existe um produto com esse nome");
        }
        try {
            return produtoRepository.save(produto);
        } catch (Exception e) {
            throw new ProdutoException("Erro ao salvar o produto: " + e.getMessage());
        }
    }


    public Optional<Produto> findById(Long id) throws ProdutoException {
        Optional<Produto> existingProduto = produtoRepository.findById(id);
        if(existingProduto.isEmpty()){
            throw  new ProdutoException("Produto Not Found");
        }
        return existingProduto;
    }


    public void deletById(Long id) throws ProdutoException {
        Optional<Produto> existingProduto = produtoRepository.findById(id);
        if(existingProduto.isEmpty()){
            throw  new ProdutoException("Produto Not Found");
        }
        produtoRepository.deleteById(id);
    }

    @Override
    public Produto update(Long id, Produto produto) throws ProdutoException {
        Optional<Produto> existingProduto = produtoRepository.findById(id);
        if(existingProduto.isEmpty()){
            throw  new ProdutoException("Produto Not Found");
        }
        Produto updateProduto = existingProduto.get();
        updateProduto.setNome(produto.getNome());
        updateProduto.setPreco(produto.getPreco());
        return produtoRepository.save(updateProduto);
    }
}
