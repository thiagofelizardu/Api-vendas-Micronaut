package com.phoebus.entites.DTO;


import com.phoebus.entites.Produto;

import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.io.Serializable;

@Serdeable
@Data
public class ProdutoDTO  implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nome;
    private Double preco;


    @Creator
    public ProdutoDTO(Long id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;

    }

    public static ProdutoDTO convertProdutoDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco()
        );
    }

}