package com.phoebus.entites.DTO;

import io.micronaut.core.annotation.Creator;

import io.micronaut.serde.annotation.Serdeable;


@Serdeable
public class ProdutoDTO  {

    private String nome;
    private Double preco;


    @Creator
    public ProdutoDTO(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }


}