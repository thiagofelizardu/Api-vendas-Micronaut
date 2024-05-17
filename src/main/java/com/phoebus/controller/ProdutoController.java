package com.phoebus.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

@Controller("/produto")
@ExecuteOn(TaskExecutors.IO)
public class ProdutoController {
}
