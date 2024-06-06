package com.phoebus.entites.DTO;

import com.phoebus.entites.Pedido;
import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Serdeable
@Data
public class PedidoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long pedidoId;
    private Long clienteId;
    private String nomeCliente;
    private List<ItemDoPedidoDTO> itensPedido;
    private Double valorTotal;

    @Creator
    public PedidoDTO(Long pedidoId, Long clienteId, String nomeCliente, List<ItemDoPedidoDTO> itensPedido, Double valorTotal) {
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.nomeCliente = nomeCliente;
        this.itensPedido = itensPedido;
        this.valorTotal = valorTotal;
    }

    public static PedidoDTO convertPedidoDTO(Pedido pedido) {
        List<ItemDoPedidoDTO> itensPedido = pedido.getItensPedido().stream()
                .map(ItemDoPedidoDTO::convertItemPedidoDTO)
                .collect(Collectors.toList());

        Double valorTotal = itensPedido.stream()
                .mapToDouble(item -> item.getPrecoProduto() * item.getQuantidade())
                .sum();

        return new PedidoDTO(
                pedido.getId(),
                pedido.getClient().getId(),
                pedido.getClient().getNome(),
                itensPedido,
                valorTotal
        );
    }
}