package com.phoebus.service.serviceImpl;

import com.phoebus.entites.DTO.ProdutoDTO;
import com.phoebus.entites.Loja;
import com.phoebus.entites.Produto;
import com.phoebus.exception.ProdutoException;
import com.phoebus.repository.LojaRepository;
import com.phoebus.repository.ProdutoRepository;
import com.phoebus.service.ProdutoService;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    private final ProdutoRepository produtoRepository;
    @Inject
    private final LojaRepository lojaRepository;

    public List<Produto> listAll() {
        return produtoRepository.findAll();
    }

    public Produto savedProduto(@NonNull Long lojaId, ProdutoDTO produtoDTO) throws ProdutoException {
        Optional<Loja> existingLoja = lojaRepository.findById(lojaId);
        if (existingLoja.isEmpty()) {
            throw new ProdutoException("Loja não encontrada com id:" + lojaId);
        }
        Optional<Produto> existingProduto = produtoRepository.findByNomeAndLojaId(produtoDTO.getNome(), lojaId);
        if (existingProduto.isPresent()) {
            throw new ProdutoException("Já existe um produto com esse nome " +produtoDTO.getNome()+ " na loja com esse id: " + lojaId );
        }
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setLoja(existingLoja.get());
        try {
            return produtoRepository.save(produto);
        } catch (Exception e) {
            throw new ProdutoException("Erro ao salvar o produto: " + e.getMessage());
        }
    }



    public Optional<Produto> findById(Long id) throws ProdutoException {
        Optional<Produto> existingProduto = produtoRepository.findById(id);
        if(existingProduto.isEmpty()){
            throw  new ProdutoException("Produto não encontrado com esse id :"+id);
        }
        return existingProduto;
    }

    public Optional<Produto> findByNome(Produto produto)throws ProdutoException{
        Optional<Produto> existingProduto = produtoRepository.findByNome(produto.getNome());
        if (existingProduto.isEmpty()) {
            throw new ProdutoException("Produto não encontrado com esse nome: " + produto.getNome());
        }else {
            return produtoRepository.findByNome(produto.getNome());
        }
    }


    public void deletById(Long id) throws ProdutoException {
        Optional<Produto> existingProduto = produtoRepository.findById(id);
        if(existingProduto.isEmpty()){
            throw  new ProdutoException("Produto não encontrado com esse id :"+id);
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
