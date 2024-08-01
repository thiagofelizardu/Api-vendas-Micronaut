package com.phoebus.model.entites.DTO;

import com.phoebus.model.entites.Product;

import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.io.Serializable;

@Serdeable
@Data
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nome;
    private Double preco;


    @Creator
    public ProductDTO(Long id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;

    }

    public static ProductDTO convertProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

}