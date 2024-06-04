package com.phoebus.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity(name = "tb_pedido")
@RequiredArgsConstructor
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnore
    private Cliente client;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ItemPedido> itensPedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedidos) {
        this.itensPedido = itensPedidos;
    }

}
