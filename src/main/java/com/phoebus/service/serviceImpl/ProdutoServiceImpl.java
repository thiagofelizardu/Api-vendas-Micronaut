package com.phoebus.service.serviceImpl;

import com.phoebus.entites.Produto;
import com.phoebus.exception.ProdutoException;
import com.phoebus.repository.ProdutoRepository;
import com.phoebus.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ProdutoServiceImpl implements ProdutoService {


    @Inject
    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listAll() {
        return produtoRepository.findAll();
    }

    public Produto save(Produto produto) throws ProdutoException {
        Optional<Produto> existingProduto = produtoRepository.findByNome(produto.getNome());
        if (existingProduto.isPresent()) {
            throw new ProdutoException("Já existe um produto com esse nome :" + produto.getNome());
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
            throw  new ProdutoException("Produto Not Found with id:"+id);
        }
        return existingProduto;
    }


    public void deletById(Long id) throws ProdutoException {
        Optional<Produto> existingProduto = produtoRepository.findById(id);
        if(existingProduto.isEmpty()){
            throw  new ProdutoException("Produto Not Found with id:"+id);
        }
        produtoRepository.deleteById(id);
    }

    public Produto updateProduto(Long id, Produto produto) throws ProdutoException {
        Optional<Produto> existingProduto = produtoRepository.findById(id);
        if(existingProduto.isEmpty()){
            throw  new ProdutoException("Produto Not Found with id: "+id);
        }
        Produto updateProduto = existingProduto.get();
        updateProduto.setNome(produto.getNome());
        updateProduto.setPreco(produto.getPreco());
        return produtoRepository.save(updateProduto);
    }
}
