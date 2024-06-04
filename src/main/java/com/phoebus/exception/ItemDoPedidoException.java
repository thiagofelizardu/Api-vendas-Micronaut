package com.phoebus.exception;

public class ItemDoPedidoException extends  Exception{
    public ItemDoPedidoException(Long id) {
        super(String.format("Item do Pedido não encontrado com esse id: " + id));
    }
}
