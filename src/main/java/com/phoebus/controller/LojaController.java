package com.phoebus.controller;

import com.phoebus.service.LojaService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

@Controller("/loja")
@ExecuteOn(TaskExecutors.IO)
public class LojaController {

    @Inject
    private LojaService lojaService;




}
