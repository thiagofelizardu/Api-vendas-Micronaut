package com.phoebus.controller;

import com.phoebus.entites.DTO.ProdutoDTO;
import com.phoebus.entites.Produto;
import com.phoebus.exception.ProdutoException;
import com.phoebus.s3.S3Service;
import com.phoebus.service.ProdutoService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;
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

    @Post()
    @Status(HttpStatus.CREATED)
    public ProdutoDTO produtoSaved( @Body ProdutoDTO produtoDTO) throws ProdutoException {
        return produtoService.saveProduto( produtoDTO);
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

    @Status(HttpStatus.OK)
    @Post(value = "/upload")
    public void uploadToProdutoMinIO( @Body ProdutoDTO produtoDTO) {
        produtoService.uploadProdutoMinIO(produtoDTO);
    }

}
