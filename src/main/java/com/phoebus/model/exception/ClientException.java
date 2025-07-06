package com.phoebus.model.exception;

public class ClientException extends Exception{
    public ClientException(Long id) {
        super(String.format("Cliente não encontrado com esse id: %s", id));
    }
}
