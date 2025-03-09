package com.phoebus.model.exception;

public class ProductException extends Exception {
    public ProductException(Long id) {
        super(String.format(("Produto não encontrado com esse id: " + id)));
    }
    public ProductException(String nome) {
        super(String.format("Já existe um produto com o nome: %s", nome));
    }
}
