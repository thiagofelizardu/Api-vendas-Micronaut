package com.phoebus.controller;

import com.phoebus.entites.DTO.ProdutoDTO;
import com.phoebus.exception.ProdutoException;
import com.phoebus.service.ProdutoService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller("/produto")
@ExecuteOn(TaskExecutors.IO)
@RequiredArgsConstructor
public class ProdutoController {

    @Inject
    private final ProdutoService produtoService;

    @Get("/")
    @Status(HttpStatus.OK)
    public List<ProdutoDTO> produtoList() {
        return produtoService.listAll();
    }

    @Post("/loja/{lojaId}")
    @Status(HttpStatus.CREATED)
    public ProdutoDTO produtoSaved(@PathVariable Long lojaId, @Body ProdutoDTO produtoDTO) throws ProdutoException {
        try {
            return produtoService.saveProduto(lojaId, produtoDTO);
        } catch (Exception e) {
            throw new ProdutoException("Erro ao salvar produto: " + e.getMessage());
        }
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public ProdutoDTO produtoFindById(@PathVariable Long id) throws ProdutoException {
        return produtoService.findById(id);
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public void produtoDeleteById(@PathVariable Long id) throws ProdutoException {
        produtoService.deleteById(id);
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    public ProdutoDTO produtoUpdate(@PathVariable Long id, @Body ProdutoDTO produtoDTO) throws ProdutoException {
        return produtoService.updateProduto(id, produtoDTO);
    }

}
