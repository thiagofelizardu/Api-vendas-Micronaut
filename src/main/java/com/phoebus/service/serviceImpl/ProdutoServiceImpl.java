package com.phoebus.service.serviceImpl;

import com.phoebus.entites.DTO.ProdutoDTO;
import com.phoebus.entites.Produto;
import com.phoebus.exception.ProdutoException;
import com.phoebus.repository.ProdutoRepository;
import com.phoebus.s3.S3Service;
import com.phoebus.service.ProdutoService;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    private final ProdutoRepository produtoRepository;

    @Inject
    private final S3Service s3Service;

    public List<ProdutoDTO> listAll() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(ProdutoDTO::convertProdutoDTO)
                .collect(Collectors.toList());
    }


    public ProdutoDTO saveProduto( ProdutoDTO produtoDTO) throws ProdutoException {
        if (produtoRepository.findByNome(produtoDTO.getNome()).isPresent()) {
            throw new ProdutoException(produtoDTO.getNome());
        }
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        try {
            produto = produtoRepository.save(produto);
            return ProdutoDTO.convertProdutoDTO(produto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o produto: " + e.getMessage());
        }
    }


    public ProdutoDTO findById(@NonNull Long id) throws ProdutoException {
        Produto existingProduto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoException(id));
        return ProdutoDTO.convertProdutoDTO(existingProduto);
    }


//    public ProdutoDTO findByNome(String nome) throws ProdutoException {
//        Produto existingProduto = produtoRepository.findByNome(nome)
//                .orElseThrow(() -> new ProdutoException(nome));
//        return ProdutoDTO.convertProdutoDTO(existingProduto);
//    }

    public void deleteById(Long id) throws ProdutoException {
        produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoException( id));
        produtoRepository.deleteById(id);
    }

    public ProdutoDTO updateProduto(Long id, ProdutoDTO produtoDTO) throws ProdutoException {
        Produto existingProduto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoException(id));
        existingProduto.setNome(produtoDTO.getNome());
        existingProduto.setPreco(produtoDTO.getPreco());
        try {
            Produto updatedProduto = produtoRepository.save(existingProduto);
            return ProdutoDTO.convertProdutoDTO(updatedProduto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o produto: " + e.getMessage());
        }
    }


    public void uploadProdutoMinIO(ProdutoDTO produtoDTO) {
        try {
            Long id = produtoDTO.getId();
            Produto produto = new Produto();
            produto.setNome(produtoDTO.getNome());
            produto.setPreco(produtoDTO.getPreco());
            String key = String.valueOf(id);
            s3Service.put(key, produto);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao fazer upload do produto: " + e.getMessage());
        }
    }
}

