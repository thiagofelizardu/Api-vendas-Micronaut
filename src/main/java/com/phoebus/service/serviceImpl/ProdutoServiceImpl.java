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
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    private final ProdutoRepository produtoRepository;
    @Inject
    private final LojaRepository lojaRepository;


    public List<ProdutoDTO> listAll() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(ProdutoDTO::convertProdutoDTO)
                .collect(Collectors.toList());
    }


    public ProdutoDTO saveProduto(@NonNull Long lojaId, ProdutoDTO produtoDTO) throws ProdutoException {
        Optional<Loja> existingLoja = lojaRepository.findById(lojaId);
        if (existingLoja.isEmpty()) {
            throw new ProdutoException("Loja não encontrada com id: " + lojaId);
        }
        Optional<Produto> existingProduto = produtoRepository.findByNomeAndLojaId(produtoDTO.getNome(), lojaId);
        if (existingProduto.isPresent()) {
            throw new ProdutoException("Já existe um produto com esse nome " + produtoDTO.getNome() + " na loja com esse id: " + lojaId);
        }
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setLoja(existingLoja.get());
        try {
            produto = produtoRepository.save(produto);
            return ProdutoDTO.convertProdutoDTO(produto);
        } catch (Exception e) {
            throw new ProdutoException("Erro ao salvar o produto: " + e.getMessage());
        }
    }


    public ProdutoDTO findById(@NonNull Long id) throws ProdutoException {
        Optional<Produto> existingProduto = produtoRepository.findById(id);
        if (existingProduto.isEmpty()) {
            throw new ProdutoException("Produto não encontrado com esse id: " + id);
        }
        return ProdutoDTO.convertProdutoDTO(existingProduto.get());
    }


    public ProdutoDTO findByNome(String nome) throws ProdutoException {
        Optional<Produto> existingProduto = produtoRepository.findByNome(nome);
        if (existingProduto.isEmpty()) {
            throw new ProdutoException("Produto não encontrado com esse nome: " + nome);
        }
        return ProdutoDTO.convertProdutoDTO(existingProduto.get());
    }


    public void deleteById(Long id) throws ProdutoException {
        Optional<Produto> existingProduto = produtoRepository.findById(id);
        if (existingProduto.isEmpty()) {
            throw new ProdutoException("Produto não encontrado com esse id: " + id);
        }
        produtoRepository.deleteById(id);
    }


    public ProdutoDTO updateProduto(Long id, ProdutoDTO produtoDTO) throws ProdutoException {
        Optional<Produto> existingProduto = produtoRepository.findById(id);
        if (existingProduto.isEmpty()) {
            throw new ProdutoException("Produto não encontrado com esse id: " + id);
        }
        Produto updateProduto = existingProduto.get();
        updateProduto.setNome(produtoDTO.getNome());
        updateProduto.setPreco(produtoDTO.getPreco());
        try {
            updateProduto = produtoRepository.save(updateProduto);
            return ProdutoDTO.convertProdutoDTO(updateProduto);
        } catch (Exception e) {
            throw new ProdutoException("Erro ao atualizar o produto: " + e.getMessage());
        }
    }
}
