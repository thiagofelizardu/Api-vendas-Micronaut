package com.phoebus.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Entity(name = "produto")
@RequiredArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    @NotNull
    private Long preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPreco() {
        return preco;
    }

    public void setPreco(Long preco) {
        this.preco = preco;
    }
}
