package com.phoebus.controller;

import com.phoebus.entites.Loja;
import com.phoebus.exception.LojaException;
import com.phoebus.service.LojaService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Controller("/loja")
@ExecuteOn(TaskExecutors.IO)
public class LojaController {

    @Inject
    private final LojaService lojaService;


    public LojaController(LojaService lojaService) {
        this.lojaService = lojaService;
    }

    @Get("/")
    @Status(HttpStatus.OK)
    public List<Loja> lojaList(){
        return lojaService.listAll();
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Loja lojaSaved(@Body Loja loja){
        return lojaService.save(loja);
    }
    @Get("/{id}")
    @Status(HttpStatus.OK)
    public Optional<Loja> lojaFindById(@PathVariable Long id)throws LojaException{
        return lojaService.findById(id);
    }


    @Delete("/{id}")
    public void lojaDeleteById(@PathVariable Long id)throws LojaException{
        lojaService.deletById(id);
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    public Loja lojaUpdate(@PathVariable Long id, @Body Loja loja) throws LojaException {
        return lojaService.update(id, loja);
    }

}
