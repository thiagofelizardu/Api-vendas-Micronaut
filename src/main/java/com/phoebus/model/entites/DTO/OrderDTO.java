package com.phoebus.model.entites.DTO;

import com.phoebus.model.entites.Order;
import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Serdeable
@Data
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long orderId;
    private Long clientId;
    private String nameClient;
    private List<OrderItemDTO> orderItem;
    private Double amount;

    @Creator
    public OrderDTO(Long orderId, Long clientId, String nameClient, List<OrderItemDTO> orderItem, Double amount) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.nameClient = nameClient;
        this.orderItem = orderItem;
        this.amount = amount;
    }

    public static OrderDTO convertPedidoDTO(Order order) {
        List<OrderItemDTO> itensPedido = order.getOrderItems().stream()
                .map(OrderItemDTO::convertOrderItemDTO)
                .collect(Collectors.toList());

        Double valorTotal = itensPedido.stream()
                .mapToDouble(item -> item.getPriceProduct() * item.getQuantity())
                .sum();

        return new OrderDTO(
                order.getId(),
                order.getClient().getId(),
                order.getClient().getName(),
                itensPedido,
                valorTotal
        );
    }
}