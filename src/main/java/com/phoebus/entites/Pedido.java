package com.phoebus.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity(name = "tb_pedido")
@Getter
@Setter
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

    @Column(name = "valorTotal")
    private Double valorTotal;

}
