package com.phoebus.model.entites.DTO;

import com.phoebus.model.entites.Order;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Serdeable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long orderId;
    private Long clientId;
    private String nameClient;
    private List<OrderItemDTO> orderItem;
    private Double amount;

    public static OrderDTO convertOrderDTO(Order order) {
        List<OrderItemDTO> itensPedido = order.getOrderItems().stream()
                .map(OrderItemDTO::convertOrderItemDTO)
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getId(),
                order.getClient().getId(),
                order.getClient().getName(),
                itensPedido,
                order.getAmount()
        );
    }
}