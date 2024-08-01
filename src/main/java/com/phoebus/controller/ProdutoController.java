package com.phoebus.controller;

import com.phoebus.model.entites.DTO.ProductDTO;
import com.phoebus.model.exception.ProdutoException;
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
    public List<ProductDTO> produtoList() {
        return produtoService.listAll();
    }

    @Post()
    @Status(HttpStatus.CREATED)
    public ProductDTO produtoSaved(@Body ProductDTO productDTO) throws ProdutoException {
        return produtoService.saveProduto(productDTO);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public ProductDTO produtoFindById(@PathVariable Long id) throws ProdutoException {
        return produtoService.findById(id);
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public void produtoDeleteById(@PathVariable Long id) throws ProdutoException {
        produtoService.deleteById(id);
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    public ProductDTO produtoUpdate(@PathVariable Long id, @Body ProductDTO productDTO) throws ProdutoException {
        return produtoService.updateProduto(id, productDTO);
    }

}
