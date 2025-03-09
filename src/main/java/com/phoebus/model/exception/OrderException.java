package com.phoebus.model.exception;

public class OrderException extends Exception {
    public OrderException(Long id) {
        super(String.format("Pedido não encontrado com esse id:" + id));
    }
}
