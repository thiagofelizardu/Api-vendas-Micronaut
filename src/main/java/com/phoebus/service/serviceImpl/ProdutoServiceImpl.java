package com.phoebus.service.serviceImpl;

import com.phoebus.model.entites.DTO.ProductDTO;
import com.phoebus.model.entites.Product;
import com.phoebus.model.exception.ProductException;
import com.phoebus.repository.ProductRepository;
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
    private final ProductRepository productRepository;

    public List<ProductDTO> listAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductDTO::convertProductDTO)
                .collect(Collectors.toList());
    }


    public ProductDTO saveProduto(ProductDTO productDTO) throws ProductException {
        if (productRepository.findByName(productDTO.getName()).isPresent()) {
            throw new ProductException(productDTO.getName());
        }
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        try {
            product = productRepository.save(product);
            return ProductDTO.convertProductDTO(product);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o produto: " + e.getMessage());
        }
    }


    public ProductDTO findById(@NonNull Long id) throws ProductException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductException(id));
        return ProductDTO.convertProductDTO(existingProduct);
    }


    public ProductDTO findByNome(String name) throws ProductException {
        Product existingProduct = productRepository.findByName(name)
                .orElseThrow(() -> new ProductException(name));
        return ProductDTO.convertProductDTO(existingProduct);
    }

    public void deleteById(Long id) throws ProductException {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductException( id));
        productRepository.deleteById(id);
    }

    public ProductDTO updateProduto(Long id, ProductDTO productDTO) throws ProductException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductException(id));
        existingProduct.setName(productDTO.getName());
        existingProduct.setPrice(productDTO.getPrice());
        try {
            Product updatedProduct = productRepository.save(existingProduct);
            return ProductDTO.convertProductDTO(updatedProduct);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o produto: " + e.getMessage());
        }
    }

}

