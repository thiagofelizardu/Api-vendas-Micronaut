package com.phoebus.exception;

public class ProdutoException extends Exception {

    public ProdutoException(Long id) {
        super(String.format(("Produto não encontrado com esse id: " + id)));
    }
    public ProdutoException(String nome) {
        super(String.format("Já existe um produto com o nome: %s", nome));
    }

}
