package com.phoebus.entites.DTO;

import com.phoebus.entites.ItemPedido;
import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.io.Serializable;

@Serdeable
@Data
public class ItemDoPedidoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long produtoId;
    private String nomeProduto;
    private Double precoProduto;
    private Long quantidade;

    @Creator
    public ItemDoPedidoDTO(Long produtoId, String nomeProduto, Double precoProduto, Long quantidade) {
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.quantidade = quantidade;
    }

    public static ItemDoPedidoDTO convertItemPedidoDTO(ItemPedido itemPedido) {
        return new ItemDoPedidoDTO(
                itemPedido.getId(),
                itemPedido.getProduto().getNome(),
                itemPedido.getProduto().getPreco(),
                itemPedido.getQuantidade()
        );
    }
}
