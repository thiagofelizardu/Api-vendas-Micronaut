package com.phoebus.model.exception;

public class OrderItemException extends  Exception{
    public OrderItemException(Long id) {
        super(String.format("Item do Pedido não encontrado com esse id: " + id));
    }
}
