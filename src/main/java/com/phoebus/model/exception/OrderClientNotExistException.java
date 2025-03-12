package com.phoebus.model.exception;

public class OrderClientNotExistException extends RuntimeException {
    public OrderClientNotExistException(String cpf) {
        super(String.format("O pedido não pertence ao cliente com esse cpf : %s.", cpf));
    }
}
