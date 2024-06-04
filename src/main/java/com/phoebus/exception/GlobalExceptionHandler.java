package com.phoebus.exception;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;

@Controller
public class GlobalExceptionHandler extends RuntimeException{

    @Error
    public HttpResponse<StandardError> handleClientNotFound(ClientException ex, HttpRequest request) {
        String error = "Cliente não encontrado com esse id!";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(status ,error, ex.getMessage(), request.getPath());
        return HttpResponse.status(status).body(err);
    }

    @Error
    public HttpResponse<StandardError> handleProdutoNotFound(ProdutoException ex, HttpRequest<?> request) {
        String error = ex.getMessage().contains("Produto não encontrado com esse id:") ? "Produto não encontrado!" : "Erro de produto";
        HttpStatus status = ex.getMessage().contains("Produto não encontrado com esse id:") ? HttpStatus.NOT_FOUND : HttpStatus.CONFLICT;
        StandardError err = new StandardError(status, error, ex.getMessage(), request.getPath());
        return HttpResponse.status(status).body(err);
    }

    @Error
    public HttpResponse<StandardError> handleProdutoException(ProdutoException ex, HttpRequest<?> request) {
        String error = ex.getMessage().contains("Já existe um produto com o nome") ? "Conflito de Produto" : "Erro de Produto";
        HttpStatus status = ex.getMessage().contains("Já existe um produto com o nome") ? HttpStatus.CONFLICT : HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(status, error, ex.getMessage(), request.getPath());
        return HttpResponse.status(status).body(err);
    }


}
