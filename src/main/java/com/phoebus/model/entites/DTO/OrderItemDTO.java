package com.phoebus.model.entites.DTO;

import com.phoebus.model.entites.OrderItem;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Serdeable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long produtoId;
    private String nameProduct;
    private Double priceProduct;
    private Long quantity;

    public static OrderItemDTO convertOrderItemDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getProduct().getName(),
                orderItem.getProduct().getPrice(),
                orderItem.getQuantity()
        );
    }
}
