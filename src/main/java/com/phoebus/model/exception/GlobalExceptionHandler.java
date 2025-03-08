package com.phoebus.model.exception;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
@Controller
public class GlobalExceptionHandler {

    @Error(global = true)
    public HttpResponse<?> handle(HttpRequest<?> request, Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.getCode());
        response.put("error", "Erro interno no servidor");
        response.put("message", exception.getMessage());

        return HttpResponse.serverError(response);
    }
}