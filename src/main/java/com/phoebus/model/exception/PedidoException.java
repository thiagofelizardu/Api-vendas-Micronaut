package com.phoebus.model.exception;

public class PedidoException extends Exception {
    public PedidoException(Long id) {
        super(String.format("Pedido não encontrado com esse id:" + id));
    }
}
