package com.phoebus.controller;

import com.phoebus.entites.Produto;
import com.phoebus.exception.ProdutoException;
import com.phoebus.service.ProdutoService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Controller("/produto")
@ExecuteOn(TaskExecutors.IO)
public class ProdutoController {

    @Inject
    private final ProdutoService produtoService;


    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Get("/")
    @Status(HttpStatus.OK)
    public List<Produto> produtoList(){
        return produtoService.listAll();
    }
    @Post
    @Status(HttpStatus.CREATED)
    public Produto produtoSaved(@Body Produto produto)throws ProdutoException  {
        return produtoService.save(produto);
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public Optional<Produto> produtoFindById(@PathVariable Long id)throws ProdutoException {
        return produtoService.findById(id);
    }


    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public void produtoDeleteById(@PathVariable Long id)throws ProdutoException {
        produtoService.deletById(id);
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    public Produto produtoUpdate(@PathVariable Long id, @Body Produto produto) throws ProdutoException {
        return produtoService.updateProduto(id,produto);
    }

}
