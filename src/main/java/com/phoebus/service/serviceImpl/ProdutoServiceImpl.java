package com.phoebus.service.serviceImpl;

import com.phoebus.model.entites.DTO.ProductDTO;
import com.phoebus.model.entites.Product;
import com.phoebus.model.exception.ProdutoException;
import com.phoebus.repository.ProdutoRepository;
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

    public List<ProductDTO> listAll() {
        List<Product> products = produtoRepository.findAll();
        return products.stream()
                .map(ProductDTO::convertProductDTO)
                .collect(Collectors.toList());
    }


    public ProductDTO saveProduto(ProductDTO productDTO) throws ProdutoException {
        if (produtoRepository.findByName(productDTO.getNome()).isPresent()) {
            throw new ProdutoException(productDTO.getNome());
        }
        Product product = new Product();
        product.setName(productDTO.getNome());
        product.setPrice(productDTO.getPreco());
        try {
            product = produtoRepository.save(product);
            return ProductDTO.convertProductDTO(product);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o produto: " + e.getMessage());
        }
    }


    public ProductDTO findById(@NonNull Long id) throws ProdutoException {
        Product existingProduct = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoException(id));
        return ProductDTO.convertProductDTO(existingProduct);
    }


    public ProductDTO findByNome(String name) throws ProdutoException {
        Product existingProduct = produtoRepository.findByName(name)
                .orElseThrow(() -> new ProdutoException(name));
        return ProductDTO.convertProductDTO(existingProduct);
    }

    public void deleteById(Long id) throws ProdutoException {
        produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoException( id));
        produtoRepository.deleteById(id);
    }

    public ProductDTO updateProduto(Long id, ProductDTO productDTO) throws ProdutoException {
        Product existingProduct = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoException(id));
        existingProduct.setName(productDTO.getNome());
        existingProduct.setPrice(productDTO.getPreco());
        try {
            Product updatedProduct = produtoRepository.save(existingProduct);
            return ProductDTO.convertProductDTO(updatedProduct);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o produto: " + e.getMessage());
        }
    }

}

