package com.phoebus.model.entites.DTO;

import com.phoebus.model.entites.OrderItem;
import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.io.Serializable;

@Serdeable
@Data
public class OrderItemDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long produtoId;
    private String nameProduct;
    private Double priceProduct;
    private Long quantity;

    @Creator
    public OrderItemDTO(Long produtoId, String nameProduct, Double priceProduct, Long quantity) {
        this.produtoId = produtoId;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.quantity = quantity;
    }

    public static OrderItemDTO convertOrderItemDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getProduct().getName(),
                orderItem.getProduct().getPrice(),
                orderItem.getQuantity()
        );
    }
}
