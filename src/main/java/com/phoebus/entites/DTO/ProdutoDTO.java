package com.phoebus.entites.DTO;

import com.phoebus.entites.Loja;
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
    private Loja loja;

    @Creator
    public ProdutoDTO(Long id, String nome, Double preco, Loja loja) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.loja = loja;
    }

    public static ProdutoDTO convertProdutoDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getLoja()
        );
    }

}